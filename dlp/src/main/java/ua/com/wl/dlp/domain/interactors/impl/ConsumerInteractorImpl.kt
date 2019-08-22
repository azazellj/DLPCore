package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.*

import android.app.Application

import ua.com.wl.dlp.R
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.ConsumerApiV1
import ua.com.wl.dlp.data.api.ConsumerApiV3
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.consumer.feedback.feedback
import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.ReferralRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.ReferralResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.ApiException
import ua.com.wl.dlp.domain.exeptions.consumer.referral.ReferralException
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.utils.createBroadcastMessage
import ua.com.wl.dlp.utils.toPrefs

/**
 * @author Denis Makovskyi
 */

class ConsumerInteractorImpl(
    private val app: Application,
    private val apiV1: ConsumerApiV1,
    private val apiV3: ConsumerApiV3,
    errorsMapper: ErrorsMapper,
    consumerPreferences: ConsumerPreferences) : ConsumerInteractor, UseCase(errorsMapper) {

    private var profilePrefs = consumerPreferences.profilePrefs

    override suspend fun getProfile(): Result<ProfileResponse> =
        callApi(call = { apiV3.getProfile() }).sfMap { data ->
            data?.payload?.also { profile ->
                withContext(Dispatchers.IO) {
                    val snapshot = profilePrefs.copy()
                    profilePrefs = profile.toPrefs()
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
                    val snapshot = profilePrefs.copy()
                    profilePrefs = profile.toPrefs()
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }
        }

    override suspend fun useReferralCode(code: String): Result<ReferralResponse> =
        callApi(
            call = { apiV3.useReferralCode(ReferralRequest(code)) },
            errorClass = ReferralException::class.java
        ).sfMap { data ->
            data?.payload?.also { referral ->
                withContext(Dispatchers.IO) {
                    val snapshot = profilePrefs.copy()
                    profilePrefs = profilePrefs.copy(balance = referral.balance, inviteCode = referral.inviteCode)
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
                    val snapshot = profilePrefs.copy()
                    profilePrefs = profilePrefs.copy(qrCode = qr.qrCode)
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
                    app.getString(R.string.dlp_feedback_callback_agree)
                } else {
                    app.getString(R.string.dlp_feedback_callback_disagree)
                }
                this.message = "$message\n\n${app.getString(R.string.dlp_feedback_callback_prefix)}: $answer"
                this.appVersion = "${app.getString(R.string.dlp_feedback_app_version)}$appVersion"
                this.phone = profilePrefs.phone
                this.email = profilePrefs.email
            }

        } catch (e: Exception) {
            return Result.Failure(ApiException())
        }

        return callApi(call = { apiV1.feedback(request) })
    }

    private fun notifyProfileChanges(snapshot: ProfilePrefs) {
        if (snapshot.firstName != profilePrefs.firstName) {
            CoreBusEventsFactory.firstName(profilePrefs.firstName, true)
        }
        if (snapshot.patronymic != profilePrefs.patronymic) {
            CoreBusEventsFactory.patronymic(profilePrefs.patronymic, true)
        }
        if (snapshot.lastName != profilePrefs.lastName) {
            CoreBusEventsFactory.lastName(profilePrefs.lastName, true)
        }
        if (snapshot.city != profilePrefs.city) {
            CoreBusEventsFactory.city(profilePrefs.city, true)
        }
        if (snapshot.phone != profilePrefs.phone) {
            CoreBusEventsFactory.phone(profilePrefs.phone, true)
        }
        if (snapshot.email != profilePrefs.email) {
            CoreBusEventsFactory.email(profilePrefs.email, true)
        }
        if (snapshot.balance != profilePrefs.balance) {
            createBroadcastMessage(app, Constants.RECEIVER_ACTION_SOUND_BONUSES)
            CoreBusEventsFactory.balance(profilePrefs.balance, true)
        }
        if (snapshot.qrCode != profilePrefs.qrCode) {
            CoreBusEventsFactory.qrCode(profilePrefs.qrCode, true)
        }
        if (snapshot.inviteCode != profilePrefs.inviteCode) {
            CoreBusEventsFactory.inviteCode(profilePrefs.inviteCode, true)
        }
        if (snapshot.referralCode != profilePrefs.referralCode) {
            CoreBusEventsFactory.referralCode(profilePrefs.referralCode, true)
        }
    }
}