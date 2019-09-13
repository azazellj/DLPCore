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
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.api.consumer.referral.ReferralException
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.utils.createBroadcastMessage
import ua.com.wl.dlp.utils.only
import ua.com.wl.dlp.utils.toPrefs

/**
 * @author Denis Makovskyi
 */

class ConsumerInteractorImpl(
    private val app: Application,
    errorsMapper: ErrorsMapper,
    private val apiV1: ConsumerApiV1,
    private val apiV3: ConsumerApiV3,
    private val consumerPreferences: ConsumerPreferences) : ConsumerInteractor, UseCase(errorsMapper) {

    override suspend fun getProfile(): Result<ProfileResponse> =
        callApi(call = { apiV3.getProfile() })
            .flatMap { dataResponse ->
                dataResponse.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }.sOnSuccess { profileResponse ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = profileResponse.toPrefs()
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }

    override suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse> =
        callApi(call = { apiV3.updateProfile(profile) })
            .flatMap { dataResponse ->
                dataResponse.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }.sOnSuccess { profileResponse ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = profileResponse.toPrefs()
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }

    override suspend fun useReferralCode(code: String): Result<ReferralResponse> =
        callApi(
            call = { apiV3.useReferralCode(ReferralRequest(code)) },
            errorClass = ReferralException::class.java
        ).flatMap { dataResponse ->
            dataResponse.ifPresentOrDefault(
                { Result.Success(it.payload) },
                { Result.Failure(ApiException()) })
        }.sOnSuccess { referralResponse ->
            withContext(Dispatchers.IO) {
                val snapshot = consumerPreferences.profilePrefs.copy()
                consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(
                    balance = referralResponse.balance,
                    inviteCode = referralResponse.inviteCode)
                withContext(Dispatchers.Main.immediate) {
                    notifyProfileChanges(snapshot)
                }
            }
        }

    override suspend fun getQrCode(): Result<QrCodeResponse> =
        callApi(call = { apiV1.getQrCode() })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }.sOnSuccess { qrResponse ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(qrCode = qrResponse.qrCode)
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }

    override suspend fun loadTransactionsHistory(
        page: Int?,
        count: Int?

    ): Result<PagedResponse<TransactionResponse>> =
        callApi(call = { apiV3.loadTransactionsHistory(page, count) }).flatMap { dataResponse ->
            dataResponse.ifPresentOrDefault(
                { Result.Success(it.payload) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun feedback(
        message: String,
        appVersion: String,
        callback: Boolean

    ): Result<FeedbackResponse> =
        feedback {
            val answer = if (callback) {
                app.getString(R.string.dlp_feedback_callback_agree)
            } else {
                app.getString(R.string.dlp_feedback_callback_disagree)
            }
            this.message = "$message\n\n${app.getString(R.string.dlp_feedback_callback_prefix)}: $answer"
            this.appVersion = "${app.getString(R.string.dlp_feedback_app_version)}$appVersion"
            this.phone = consumerPreferences.profilePrefs.phone
            this.email = consumerPreferences.profilePrefs.email

        }.let {
            callApi(call = { apiV1.feedback(it) }).flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
        }


    private fun notifyProfileChanges(snapshot: ProfilePrefs) {
        val changes: MutableList<ProfileBusEvent.Change> = mutableListOf()
        if (snapshot.firstName != consumerPreferences.profilePrefs.firstName) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.FIRST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.firstName)).only { changes.add(it) }
        }
        if (snapshot.patronymic != consumerPreferences.profilePrefs.patronymic) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PATRONYMIC,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.patronymic)).only { changes.add(it) }
        }
        if (snapshot.lastName != consumerPreferences.profilePrefs.lastName) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.LAST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.lastName)).only { changes.add(it) }
        }
        if (snapshot.city != consumerPreferences.profilePrefs.city) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.CITY,
                ProfileBusEvent.FieldValue.CityObjectValue(consumerPreferences.profilePrefs.city)).only { changes.add(it) }
        }
        if (snapshot.phone != consumerPreferences.profilePrefs.phone) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PHONE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.phone)).only { changes.add(it) }
        }
        if (snapshot.email != consumerPreferences.profilePrefs.email) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.EMAIL,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.email)).only { changes.add(it) }
        }
        if (snapshot.gender != consumerPreferences.profilePrefs.gender) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.GENDER,
                ProfileBusEvent.FieldValue.GenderObjectValue(consumerPreferences.profilePrefs.gender)).only { changes.add(it) }
        }
        if (snapshot.birthDate != consumerPreferences.profilePrefs.birthDate) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BIRTH_DATE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.birthDate)).only { changes.add(it) }
        }
        if (snapshot.balance != consumerPreferences.profilePrefs.balance) {
            createBroadcastMessage(app, Constants.RECEIVER_ACTION_SOUND_BONUSES)
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BALANCE,
                ProfileBusEvent.FieldValue.LongValue(consumerPreferences.profilePrefs.balance)).only { changes.add(it) }
        }
        if (snapshot.qrCode != consumerPreferences.profilePrefs.qrCode) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.QR_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.qrCode)).only { changes.add(it) }
        }
        if (snapshot.inviteCode != consumerPreferences.profilePrefs.inviteCode) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.INVITE_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.inviteCode)).only { changes.add(it) }
        }
        if (snapshot.referralCode != consumerPreferences.profilePrefs.referralCode) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.REFERRAL_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.referralCode)).only { changes.add(it) }
        }
        CoreBusEventsFactory.profileChanges(changes)
    }
}