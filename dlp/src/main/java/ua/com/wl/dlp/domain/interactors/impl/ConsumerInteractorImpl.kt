package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import android.app.Application

import ua.com.wl.dlp.R
import ua.com.wl.dlp.data.api.ConsumerApiV1
import ua.com.wl.dlp.data.api.ConsumerApiV3
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.consumer.feedback.feedback
import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.ReferralActivationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.ReferralActivationResponse
import ua.com.wl.dlp.data.events.EventsFactory
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.ApiException
import ua.com.wl.dlp.domain.exeptions.consumer.referral.ReferralException
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.utils.toPrefs

/**
 * @author Denis Makovskyi
 */

class ConsumerInteractorImpl(
    private val application: Application,
    private val consumerPreferences: ConsumerPreferences,
    private val apiV1: ConsumerApiV1,
    private val apiV3: ConsumerApiV3,
    errorsMapper: ErrorsMapper) : ConsumerInteractor, UseCase(errorsMapper) {

    override suspend fun getProfile(): Result<ProfileResponse> =
        callApi(call = { apiV3.getProfile() }).fMap { it?.payload }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    val savedBalance = consumerPreferences.profilePrefs.balance
                    consumerPreferences.profilePrefs = it.data.toPrefs()
                    //-
                    if (savedBalance != consumerPreferences.profilePrefs.balance) {
                        EventsFactory.balance(it.data.balance, true)
                    }
                }
            }
        }

    override suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse> =
        callApi(call = { apiV3.updateProfile(profile) }).fMap { it?.payload }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    val savedBalance = consumerPreferences.profilePrefs.balance
                    consumerPreferences.profilePrefs = it.data.toPrefs()
                    //-
                    if (savedBalance != consumerPreferences.profilePrefs.balance) {
                        EventsFactory.balance(it.data.balance, true)
                    }
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
                    EventsFactory.balance(it.data.consumerBalance, true)
                }
            }
        }

    override suspend fun getQrCode(): Result<QrCodeResponse> =
        callApi(call = { apiV1.getQrCode() }).also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(qrCode = it.data.qrCode)
                }
            }
        }

    override suspend fun loadTransactionsHistory(): Result<PagedResponse<TransactionResponse>> =
        callApi(call = { apiV3.loadTransactionsHistory() }).fMap { it?.payload }

    override suspend fun feedback(message: String, appVersion: String, callback: Boolean): Result<FeedbackResponse> {
        val request = try {
            feedback {
                val answer = if (callback) {
                    application.getString(R.string.dlp_feedback_callback_agree)
                } else {
                    application.getString(R.string.dlp_feedback_callback_disagree)
                }
                this.message = "$message\n\n${application.getString(R.string.dlp_feedback_callback_prefix)}: $answer"
                this.appVersion = "${application.getString(R.string.dlp_feedback_app_version)}$appVersion"
                this.phone = consumerPreferences.profilePrefs.phone
                this.email = consumerPreferences.profilePrefs.email
            }

        } catch (e: Exception) {
            return Result.Failure(ApiException())
        }
        return callApi(call = { apiV1.feedback(request) })
    }
}