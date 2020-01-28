package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.*

import android.app.Application

import ua.com.wl.dlp.R
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.ConsumerApiV1
import ua.com.wl.dlp.data.api.ConsumerApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.consumer.feedback.feedback
import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.InvitationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.InvitationResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.api.consumer.referral.ReferralException
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.domain.interactors.OffersInteractor
import ua.com.wl.dlp.utils.sendBroadcastMessage
import ua.com.wl.dlp.utils.only
import ua.com.wl.dlp.utils.toPrefs

/**
 * @author Denis Makovskyi
 */

class ConsumerInteractorImpl constructor(
    errorsMapper: ErrorsMapper,
    private val app: Application,
    private val apiV1: ConsumerApiV1,
    private val apiV2: ConsumerApiV2,
    private val offersInteractor: OffersInteractor,
    private val consumerPreferences: ConsumerPreferences
) : UseCase(errorsMapper), ConsumerInteractor, OffersInteractor by offersInteractor {

    override suspend fun getProfile(): Result<ProfileResponse> =
        callApi(call = { apiV2.getProfile() })
            .flatMap { dataResponseOpt ->
                dataResponseOpt.ifPresentOrDefault(
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
        callApi(call = { apiV2.updateProfile(profile) })
            .flatMap { dataResponseOpt ->
                dataResponseOpt.ifPresentOrDefault(
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

    override suspend fun getQrCode(): Result<QrCodeResponse> =
        callApi(call = { apiV2.getQrCode() })
            .flatMap { dataResponseOpt ->
                dataResponseOpt.ifPresentOrDefault(
                    { Result.Success(it.payload) },
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

    override suspend fun useInviteCode(code: String): Result<InvitationResponse> =
        callApi(
            call = { apiV2.useInviteCode(InvitationRequest(code)) },
            errorClass = ReferralException::class
        ).flatMap { dataResponseOpt ->
            dataResponseOpt.ifPresentOrDefault(
                { Result.Success(it.payload) },
                { Result.Failure(ApiException()) })
        }.sOnSuccess { inviteResponse ->
            withContext(Dispatchers.IO) {
                val snapshot = consumerPreferences.profilePrefs.copy()
                consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(
                    balance = inviteResponse.balance,
                    inviteCode = inviteResponse.inviteCode)
                withContext(Dispatchers.Main.immediate) {
                    notifyProfileChanges(snapshot)
                }
            }
        }

    override suspend fun getPromoOffers(page: Int?, count: Int?): Result<PagedResponse<BaseOfferResponse>> =
        callApi(call = { apiV1.getPromoOffers(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getNoveltyOffers(page: Int?, count: Int?): Result<PagedResponse<BaseOfferResponse>> =
        callApi(call = { apiV1.getNoveltyOffers(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getTransactionsHistory(page: Int?, count: Int?): Result<PagedResponse<TransactionResponse>> =
        callApi(call = { apiV2.loadTransactionsHistory(page, count) })
            .flatMap { dataResponseOpt ->
                dataResponseOpt.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun feedback(
        message: String,
        appVersion: String,
        callback: Boolean,
        phone: String?,
        email: String?
    ): Result<FeedbackResponse> =
        feedback {
            val answer = if (callback) {
                app.getString(R.string.dlp_feedback_callback_agree)
            } else {
                app.getString(R.string.dlp_feedback_callback_disagree)
            }
            this.message = "$message\n\n${app.getString(R.string.dlp_feedback_callback_prefix)}: $answer"
            this.appVersion = "${app.getString(R.string.dlp_feedback_app_version)}$appVersion"
            this.phone = phone ?: consumerPreferences.profilePrefs.phone
            this.email = email ?: consumerPreferences.profilePrefs.email

        }.let {
            callApi(call = { apiV1.feedback(it) })
                .flatMap { responseOpt ->
                    responseOpt.ifPresentOrDefault(
                        { Result.Success(it) },
                        { Result.Failure(ApiException()) })
                }
        }


    private fun notifyProfileChanges(snapshot: ProfilePrefs) {
        val changes: MutableList<ProfileBusEvent.Change> = mutableListOf()
        if (snapshot.firstName != consumerPreferences.profilePrefs.firstName) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.FIRST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.firstName)
            ).only { changes.add(it) }
        }
        if (snapshot.patronymic != consumerPreferences.profilePrefs.patronymic) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PATRONYMIC,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.patronymic)
            ).only { changes.add(it) }
        }
        if (snapshot.lastName != consumerPreferences.profilePrefs.lastName) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.LAST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.lastName)
            ).only { changes.add(it) }
        }
        if (snapshot.city != consumerPreferences.profilePrefs.city) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.CITY,
                ProfileBusEvent.FieldValue.CityObjectValue(consumerPreferences.profilePrefs.city)
            ).only { changes.add(it) }
        }
        if (snapshot.phone != consumerPreferences.profilePrefs.phone) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PHONE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.phone)
            ).only { changes.add(it) }
        }
        if (snapshot.email != consumerPreferences.profilePrefs.email) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.EMAIL,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.email)
            ).only { changes.add(it) }
        }
        if (snapshot.gender != consumerPreferences.profilePrefs.gender) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.GENDER,
                ProfileBusEvent.FieldValue.GenderObjectValue(consumerPreferences.profilePrefs.gender)
            ).only { changes.add(it) }
        }
        if (snapshot.birthDate != consumerPreferences.profilePrefs.birthDate) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BIRTH_DATE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.birthDate)
            ).only { changes.add(it) }
        }
        if (snapshot.balance != consumerPreferences.profilePrefs.balance) {
            if (snapshot.balance != null) {
                sendBroadcastMessage(app, Constants.RECEIVER_ACTION_SOUND_BONUSES)
            }
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BALANCE,
                ProfileBusEvent.FieldValue.LongValue(consumerPreferences.profilePrefs.balance)
            ).only { changes.add(it) }
        }
        if (snapshot.moneyAmount != consumerPreferences.profilePrefs.moneyAmount) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.MONEY_AMOUNT,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.moneyAmount)
            ).only { changes.add(it) }
        }
        if (snapshot.qrCode != consumerPreferences.profilePrefs.qrCode) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.QR_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.qrCode)
            ).only { changes.add(it) }
        }
        if (snapshot.inviteCode != consumerPreferences.profilePrefs.inviteCode) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.INVITE_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.inviteCode)
            ).only { changes.add(it) }
        }
        if (snapshot.referralCode != consumerPreferences.profilePrefs.referralCode) {
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.REFERRAL_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.referralCode)
            ).only { changes.add(it) }
        }
        CoreBusEventsFactory.profileChanges(changes)
    }
}