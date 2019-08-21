package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.*

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
import ua.com.wl.dlp.data.events.factory.CoreEventsFactory
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
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
        callApi(call = { apiV3.getProfile() }).sfMap { data ->
            data?.payload?.also { profile ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = profile.toPrefs()
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }
        }

    override suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse> =
        callApi(call = { apiV3.updateProfile(profile) }).sfMap { data ->
            data?.payload?.also { profile ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = profile.toPrefs()
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }
        }

    override suspend fun activateReferralCode(code: String): Result<ReferralActivationResponse> =
        callApi(
            call = { apiV3.activateReferralCode(ReferralActivationRequest(code)) },
            errorClass = ReferralException::class.java
        ).sfMap { data ->
            data?.payload?.also { referral ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(balance = referral.consumerBalance, inviteCode = referral.inviteCode)
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }
        }

    override suspend fun getQrCode(): Result<QrCodeResponse> =
        callApi(call = { apiV1.getQrCode() }).sfMap { data ->
            data?.also { qr ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(qrCode = qr.qrCode)
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
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

    private fun notifyProfileChanges(snapshot: ProfilePrefs) {
        if (snapshot.firstName != consumerPreferences.profilePrefs.firstName) {
            CoreEventsFactory.firstName(consumerPreferences.profilePrefs.firstName, true)
        }
        if (snapshot.patronymic != consumerPreferences.profilePrefs.patronymic) {
            CoreEventsFactory.patronymic(consumerPreferences.profilePrefs.patronymic, true)
        }
        if (snapshot.lastName != consumerPreferences.profilePrefs.lastName) {
            CoreEventsFactory.lastName(consumerPreferences.profilePrefs.lastName, true)
        }
        if (snapshot.city != consumerPreferences.profilePrefs.city) {
            CoreEventsFactory.city(consumerPreferences.profilePrefs.city, true)
        }
        if (snapshot.phone != consumerPreferences.profilePrefs.phone) {
            CoreEventsFactory.phone(consumerPreferences.profilePrefs.phone, true)
        }
        if (snapshot.email != consumerPreferences.profilePrefs.email) {
            CoreEventsFactory.email(consumerPreferences.profilePrefs.email, true)
        }
        if (snapshot.balance != consumerPreferences.profilePrefs.balance) {
            CoreEventsFactory.balance(consumerPreferences.profilePrefs.balance, true)
        }
        if (snapshot.qrCode != consumerPreferences.profilePrefs.qrCode) {
            CoreEventsFactory.qrCode(consumerPreferences.profilePrefs.qrCode, true)
        }
        if (snapshot.inviteCode != consumerPreferences.profilePrefs.inviteCode) {
            CoreEventsFactory.inviteCode(consumerPreferences.profilePrefs.inviteCode, true)
        }
        if (snapshot.referralCode != consumerPreferences.profilePrefs.referralCode) {
            CoreEventsFactory.referralCode(consumerPreferences.profilePrefs.referralCode, true)
        }
    }
}