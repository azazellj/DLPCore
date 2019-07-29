package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.ConsumerApi
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.ReferralActivationRequest
import ua.com.wl.dlp.data.api.responses.PaginationResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.ReferralActivationResponse
import ua.com.wl.dlp.data.events.EventsCreator
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.consumer.referral.ReferralException
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.utils.toPrefs

/**
 * @author Denis Makovskyi
 */

class ConsumerInteractorImpl(
    private val consumerPreferences: ConsumerPreferences,
    private val api: ConsumerApi,
    errorsMapper: ErrorsMapper) : ConsumerInteractor, UseCase(errorsMapper) {

    override suspend fun getProfile(): Result<ProfileResponse> =
        callApi(call = { api.getProfile() }).fMap { it?.payload }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    consumerPreferences.profilePrefs = it.data.toPrefs()
                }
            }
        }

    override suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse> =
        callApi(call = { api.updateProfile(profile) }).fMap { it?.payload }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    consumerPreferences.profilePrefs = it.data.toPrefs()
                }
            }
        }

    override suspend fun activateReferralCode(code: String): Result<ReferralActivationResponse> =
        callApi(
            call = { api.activateReferralCode(ReferralActivationRequest(code)) },
            errorClass = ReferralException::class.java
        ).fMap {
            it?.payload
        }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(balance = it.data.consumerBalance)
                    EventsCreator.balanceInvalidationEvent(it.data.consumerBalance, true)
                }
            }
        }

    override suspend fun loadTransactionsHistory(): Result<PaginationResponse<TransactionResponse>> =
        callApi(call = { api.loadTransactionsHistory() }).fMap { it?.payload }
}