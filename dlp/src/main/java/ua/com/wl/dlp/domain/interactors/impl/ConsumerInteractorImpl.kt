package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.*

import android.app.Application

import ua.com.wl.archetype.utils.Optional

import ua.com.wl.dlp.R
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.ConsumerApiV1
import ua.com.wl.dlp.data.api.ConsumerApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.consumer.feedback.feedbackRequest
import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.InvitationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.ranks.BaseRankResponse
import ua.com.wl.dlp.data.api.responses.consumer.ranks.RankResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.InvitationResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.data.prefereces.models.RankCriteriaPrefs
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.api.consumer.referral.ReferralException
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.domain.interactors.OffersInteractor
import ua.com.wl.dlp.utils.sendBroadcastMessage
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

    override suspend fun getProfile(): Result<ProfileResponse> {
        return callApi(call = { apiV2.getProfile() })
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
    }

    override suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse> {
        return callApi(call = { apiV2.updateProfile(profile) })
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
    }

    override suspend fun getRanks(language: String): Result<PagedResponse<BaseRankResponse>> {
        return callApi(call = { apiV1.getRanks(language) })
            .flatMap { pagedResponseOpt ->
                pagedResponseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getCurrentRank(language: String): Result<Optional<BaseRankResponse>> {
        return getRanks(language)
            .flatMap { pagedResponse ->
                val currentRank = pagedResponse.items.find { rank -> rank.isCurrent }
                val currentRankIndex = pagedResponse.items.indexOf(currentRank)
                val nextRank = if (currentRankIndex > -1) pagedResponse.items[currentRankIndex + 1] else null
                Result.Success(
                    (Optional.ofNullable(currentRank) to
                            Optional.ofNullable(nextRank)))
            }.sOnSuccess { ranksOptPair ->
                ranksOptPair.first.sIfPresent { currentRank ->
                    val nextRank = ranksOptPair.second.getUnsafe()
                    val nextRankCriteria = RankCriteriaPrefs(
                        referralCount = nextRank?.selectionCriteria?.referralCount?.copy(),
                        daysRegistered = nextRank?.selectionCriteria?.daysRegistered?.copy(),
                        profileDataFilled = nextRank?.selectionCriteria?.profileDataFilled?.copy(),
                        sharingCount = nextRank?.selectionCriteria?.sharingCount?.copy(),
                        commentsCount = nextRank?.selectionCriteria?.commentsCount?.copy(),
                        paymentsCount = nextRank?.selectionCriteria?.paymentsCount?.copy(),
                        spentMoney = nextRank?.selectionCriteria?.spentMoney?.copy(),
                        spentBonuses = nextRank?.selectionCriteria?.spentBonuses?.copy(),
                        collectedBonuses = nextRank?.selectionCriteria?.collectedBonuses?.copy())
                    withContext(Dispatchers.IO) {
                        val previousRankId = consumerPreferences.rankPrefs.id
                        consumerPreferences.rankPrefs = consumerPreferences.rankPrefs.copy(
                            id = currentRank.id,
                            name = currentRank.name,
                            iconUrl = currentRank.iconUrl,
                            colorHex = currentRank.colorHex,
                            nextRankCriteria = nextRankCriteria)
                        if (previousRankId != currentRank.id) {
                            withContext(Dispatchers.Main.immediate) {
                                CoreBusEventsFactory.rankChanged(currentRank.id)
                            }
                        }
                    }
                }
            }.map { ranksOptPair -> ranksOptPair.first }
    }

    override suspend fun getRank(
        rankId: Int,
        language: String
    ): Result<RankResponse> {
        return callApi(call = { apiV1.getRank(rankId, language) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getQrCode(): Result<QrCodeResponse> {
        return callApi(call = { apiV2.getQrCode() })
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
    }

    override suspend fun activateInviteCode(request: InvitationRequest): Result<InvitationResponse> {
        return callApi(
            call = { apiV2.useInviteCode(request) },
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
    }

    override suspend fun getPromoOffers(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BaseOfferResponse>> {
        return callApi(call = { apiV1.getPromoOffers(page, count) })
            .flatMap { pagedResponseOpt ->
                pagedResponseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getNoveltyOffers(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BaseOfferResponse>> {
        return callApi(call = { apiV1.getNoveltyOffers(page, count) })
            .flatMap { pagedResponseOpt ->
                pagedResponseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getTransactionsHistory(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<TransactionResponse>> {
        return callApi(call = { apiV2.loadTransactionsHistory(page, count) })
            .flatMap { dataResponseOpt ->
                dataResponseOpt.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun feedback(
        phone: String?,
        email: String?,
        message: String,
        callback: Boolean,
        appVersion: String
    ): Result<FeedbackResponse> {
        val request = feedbackRequest {
            val answer = if (callback) {
                app.getString(R.string.dlp_feedback_callback_agree)
            } else {
                app.getString(R.string.dlp_feedback_callback_disagree)
            }
            phone { phone ?: consumerPreferences.profilePrefs.phone }
            email { email ?: consumerPreferences.profilePrefs.email }
            message { "$message\n\n${app.getString(R.string.dlp_feedback_callback_prefix)}: $answer" }
            appVersion { "${app.getString(R.string.dlp_feedback_app_version)}$appVersion" }
        }
        return callApi(call = { apiV1.feedback(request) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
        }
    }

    private fun notifyProfileChanges(snapshot: ProfilePrefs) {
        val changes = mutableListOf<ProfileBusEvent.Change>()
        if (snapshot.firstName != consumerPreferences.profilePrefs.firstName) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.FIRST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.firstName))
            changes.add(change)
        }
        if (snapshot.patronymic != consumerPreferences.profilePrefs.patronymic) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PATRONYMIC,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.patronymic))
            changes.add(change)
        }
        if (snapshot.lastName != consumerPreferences.profilePrefs.lastName) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.LAST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.lastName))
            changes.add(change)
        }
        if (snapshot.city != consumerPreferences.profilePrefs.city) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.CITY,
                ProfileBusEvent.FieldValue.CityObjectValue(consumerPreferences.profilePrefs.city))
            changes.add(change)
        }
        if (snapshot.phone != consumerPreferences.profilePrefs.phone) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PHONE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.phone))
            changes.add(change)
        }
        if (snapshot.email != consumerPreferences.profilePrefs.email) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.EMAIL,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.email))
            changes.add(change)
        }
        if (snapshot.gender != consumerPreferences.profilePrefs.gender) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.GENDER,
                ProfileBusEvent.FieldValue.GenderObjectValue(consumerPreferences.profilePrefs.gender))
            changes.add(change)
        }
        if (snapshot.birthDate != consumerPreferences.profilePrefs.birthDate) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BIRTH_DATE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.birthDate))
            changes.add(change)
        }
        if (snapshot.balance != consumerPreferences.profilePrefs.balance) {
            if (snapshot.balance != null) {
                sendBroadcastMessage(app, Constants.RECEIVER_ACTION_SOUND_BONUSES)
            }
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BALANCE,
                ProfileBusEvent.FieldValue.LongValue(consumerPreferences.profilePrefs.balance))
            changes.add(change)
        }
        if (snapshot.moneyAmount != consumerPreferences.profilePrefs.moneyAmount) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.MONEY_AMOUNT,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.moneyAmount))
            changes.add(change)
        }
        if (snapshot.qrCode != consumerPreferences.profilePrefs.qrCode) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.QR_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.qrCode))
            changes.add(change)
        }
        if (snapshot.inviteCode != consumerPreferences.profilePrefs.inviteCode) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.INVITE_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.inviteCode))
            changes.add(change)
        }
        if (snapshot.referralCode != consumerPreferences.profilePrefs.referralCode) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.REFERRAL_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.referralCode))
            changes.add(change)
        }
        CoreBusEventsFactory.profileChanges(changes)
    }
}