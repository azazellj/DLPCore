package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.ConsumerApiV1
import ua.com.wl.dlp.data.api.ConsumerApiV3
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.ReferralActivationRequest
import ua.com.wl.dlp.data.api.responses.PaginationResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.ReferralActivationResponse
import ua.com.wl.dlp.data.api.responses.consumer.shop.CityShopsResponse
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
    private val apiV1: ConsumerApiV1,
    private val apiV3: ConsumerApiV3,
    errorsMapper: ErrorsMapper) : ConsumerInteractor, UseCase(errorsMapper) {

    override suspend fun getProfile(): Result<ProfileResponse> =
        callApi(call = { apiV3.getProfile() }).fMap { it?.payload }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    consumerPreferences.profilePrefs = it.data.toPrefs()
                }
            }
        }

    override suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse> =
        callApi(call = { apiV3.updateProfile(profile) }).fMap { it?.payload }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    consumerPreferences.profilePrefs = it.data.toPrefs()
                }
            }
        }

    override suspend fun activateReferralCode(code: String): Result<ReferralActivationResponse> =
        callApi(
            call = { apiV3.activateReferralCode(ReferralActivationRequest(code)) },
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

    override suspend fun getCityShops(): Result<PaginationResponse<CityShopsResponse>> =
        callApi(call = { apiV1.getCityShops() })

    override suspend fun loadTransactionsHistory(): Result<PaginationResponse<TransactionResponse>> =
        callApi(call = { apiV3.loadTransactionsHistory() }).fMap { it?.payload }
}