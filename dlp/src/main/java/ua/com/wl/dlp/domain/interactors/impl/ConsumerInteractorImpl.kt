package ua.com.wl.dlp.domain.interactors.impl

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.common.R
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.ConsumerApiV1
import ua.com.wl.dlp.data.api.ConsumerApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.consumer.feedback.FeedbackRequest
import ua.com.wl.dlp.data.api.requests.consumer.history.notifications.NotificationsReadRequest
import ua.com.wl.dlp.data.api.requests.consumer.profile.DeleteProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.InvitationRequest
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.coupons.CouponResponse
import ua.com.wl.dlp.data.api.responses.consumer.coupons.CouponWalletResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.groups.GroupResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.notifications.NotificationsResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.transactions.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.info.BusinessResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.ranks.RankResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.InvitationResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.data.prefereces.models.RankCriteriaPrefs
import ua.com.wl.dlp.data.prefereces.models.RankPermissionsPrefs
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.api.consumer.coupons.WalletException
import ua.com.wl.dlp.domain.exeptions.api.consumer.referral.ReferralException
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.domain.interactors.OffersInteractor
import ua.com.wl.dlp.utils.*

/**
 * @author Denis Makovskyi
 */

class ConsumerInteractorImpl(
    errorsMapper: ErrorsMapper,
    private val app: Application,
    private val apiV1: ConsumerApiV1,
    private val apiV2: ConsumerApiV2,
    private val shopsDataSource: ShopsDataSource,
    private val offersInteractor: OffersInteractor,
    private val consumerPreferences: ConsumerPreferences
) : UseCase(errorsMapper), ConsumerInteractor, OffersInteractor by offersInteractor {

    override suspend fun getInfo(): Result<BusinessResponse> {
        return callApi(call = { apiV2.getInfo() })
            .fromDataResponse()
            .sOnSuccess { businessResponse ->
                withContext(Dispatchers.IO) {
                    consumerPreferences.businessPrefs = businessResponse.toPrefs()
                }
            }
    }

    override suspend fun getProfile(): Result<ProfileResponse> {
        return callApi(call = { apiV2.getProfile() })
            .fromDataResponse()
            .sOnSuccess { profileResponse ->
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
            .fromDataResponse()
            .sOnSuccess { profileResponse ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = profileResponse.toPrefs()
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }
    }

    override suspend fun getRanks(language: String): Result<PagedResponse<RankResponse>> {
        return callApi(call = { apiV1.getRanks(language) })
            .fromResponse(successMapper = {
                val ranks = it.items
                val currRank = ranks.find { rank -> rank.isCurrent }
                val currRankPriority = currRank?.priority ?: -1
                if (currRankPriority > -1) {
                    ranks.forEach { rank ->
                        if (rank.priority < currRankPriority) {
                            rank.wasReached = true
                        }
                    }
                }

                PagedResponse(
                    page = it.page, countNumber = it.count,
                    pagesCount = it.pagesCount, itemsCount = it.itemsCount,
                    nextPage = it.nextPage, previousPage = it.previousPage,
                    data = ranks, perPage = it.count, pageSize = it.count
                )
            })
    }

    override suspend fun getRank(rankId: Int, language: String): Result<RankResponse> {
        return callApi(call = { apiV1.getRank(rankId, language) })
            .fromResponse()
    }

    override suspend fun getCurrentRank(language: String): Result<Optional<RankResponse>> {
        return getRanks(language)
            .flatMap { pager ->
                val currRank = pager.items.find { rank -> rank.isCurrent }
                val nextRank = pager.items.find { rank -> rank.isNext }
                Result.Success(
                    Optional.ofNullable(currRank) to
                            Optional.ofNullable(nextRank)
                )
            }.sOnSuccess { (currRankOpt, nextRankOpt) ->
                currRankOpt.sIfPresent { currRank ->
                    val nextRank = nextRankOpt.getUnsafe()
                    val nextRankCriteria = if (nextRank != null) {
                        RankCriteriaPrefs(
                            referralCount = nextRank.selectionCriteria?.referralCount?.copy(),
                            daysRegistered = nextRank.selectionCriteria?.daysRegistered?.copy(),
                            collectedCashBack = nextRank.selectionCriteria?.collectedCashBack?.copy(),
                            profileDataFilled = nextRank.selectionCriteria?.profileDataFilled?.copy(),
                            sharingCount = nextRank.selectionCriteria?.sharingCount?.copy(),
                            commentsCount = nextRank.selectionCriteria?.commentsCount?.copy(),
                            paymentsCount = nextRank.selectionCriteria?.paymentsCount?.copy(),
                            spentMoney = nextRank.selectionCriteria?.spentMoney?.copy(),
                            spentBonuses = nextRank.selectionCriteria?.spentBonuses?.copy(),
                            collectedBonuses = nextRank.selectionCriteria?.collectedBonuses?.copy()
                        )
                    } else null
                    val currRankPermissions = RankPermissionsPrefs(
                        cashBackPercentage = currRank.permissions?.cashBackPercentage,
                        isOfferViewAllowed = currRank.permissions?.isOfferViewAllowed,
                        isOfferSharingAllowed = currRank.permissions?.isOfferSharingAllowed,
                        isArticleSharingAllowed = currRank.permissions?.isArticleSharingAllowed,
                        isPreOrderAllowed = currRank.permissions?.isPreOrderAllowed,
                        isTableReservationAllowed = currRank.permissions?.isTableReservationAllowed
                    )
                    withContext(Dispatchers.IO) {
                        val prevRankId = consumerPreferences.rankPrefs.id
                        consumerPreferences.rankPrefs = consumerPreferences.rankPrefs.copy(
                            id = currRank.id,
                            name = currRank.name,
                            iconUrl = currRank.iconUrl,
                            colorHex = currRank.colorHex,
                            nextRankCriteria = nextRankCriteria,
                            currRankPermissions = currRankPermissions
                        )
                        if (prevRankId != currRank.id) {
                            withContext(Dispatchers.Main.immediate) {
                                CoreBusEventsFactory.rankChanged(currRank.id)
                            }
                        }
                    }
                }
            }.map { (currRankOpt, _) -> currRankOpt }
    }

    override suspend fun getGroups(): Result<CollectionResponse<GroupResponse>> {
        return callApi(call = { apiV2.getGroups() })
            .fromResponse()
    }

    override suspend fun getCoupons(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<CouponResponse>> {
        return callApi(call = { apiV2.getCoupons(page, count) })
            .fromDataResponse()
    }

    override suspend fun getCoupon(id: Int): Result<CouponResponse> {
        return callApi(call = { apiV2.getCoupon(id) })
            .fromDataResponse()
    }

    override suspend fun addCouponToWallet(id: Int, barcode: String): Result<CouponWalletResponse> {
        return callApi(
            call = { apiV2.addCouponToWallet(id, barcode) },
            errorMapper = { type, cause -> WalletException(type, cause) }
        ).fromDataResponse()
    }

    override suspend fun getQrCode(): Result<QrCodeResponse> {
        return callApi(call = { apiV2.getQrCode() })
            .fromDataResponse()
            .sOnSuccess { qrResponse ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs =
                        consumerPreferences.profilePrefs.copy(qrCode = qrResponse.qrCode)
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }
    }

    override suspend fun activateInviteCode(request: InvitationRequest): Result<InvitationResponse> {
        return callApi(
            call = { apiV2.useInviteCode(request) },
            errorMapper = { type, cause -> ReferralException(type, cause) }
        )
            .fromDataResponse()
            .sOnSuccess { inviteResponse ->
                withContext(Dispatchers.IO) {
                    val snapshot = consumerPreferences.profilePrefs.copy()
                    consumerPreferences.profilePrefs = consumerPreferences.profilePrefs.copy(
                        balance = inviteResponse.balance,
                        inviteCode = inviteResponse.inviteCode
                    )
                    withContext(Dispatchers.Main.immediate) {
                        notifyProfileChanges(snapshot)
                    }
                }
            }
    }

    override suspend fun markNotificationsAsRead(request: NotificationsReadRequest): Result<Boolean> {
        return callApi(call = { apiV2.markNotificationsAsRead(request) })
            .fromResponse(successMapper = { it.isSuccessfully() })
    }

    override suspend fun feedback(
        phone: String?,
        email: String?,
        message: String,
        callback: Boolean,
        appVersion: String
    ): Result<FeedbackResponse> {
        val answer = if (callback) {
            app.getString(R.string.dlp_feedback_callback_agree)
        } else {
            app.getString(R.string.dlp_feedback_callback_disagree)
        }

        val request = FeedbackRequest(
            phone = phone ?: consumerPreferences.profilePrefs.phone,
            email = email ?: consumerPreferences.profilePrefs.email,
            message = "$message\n\n${app.getString(R.string.dlp_feedback_callback_prefix)}: $answer",
            appVersion = "${app.getString(R.string.dlp_feedback_app_version)}$appVersion",
            deviceInfo = null
        )

        return callApi(call = { apiV1.feedback(request) })
            .fromResponse()
    }

    override suspend fun getTransactionsHistory(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<TransactionResponse>> {
        return callApi(call = { apiV2.getTransactionsHistory(page, count) })
            .fromDataResponse()
    }

    override suspend fun getNotificationsHistory(
        page: Int?,
        count: Int?
    ): Result<NotificationsResponse> {
        return callApi(call = { apiV2.getNotificationsHistory(page, count) })
            .fromDataResponse()
    }

    override suspend fun getPromoOffers(
        page: Int?,
        count: Int?,
        shopId: Int?
    ): Result<PagedResponse<BaseOfferResponse>> {
        return callApi(call = { apiV1.getPromoOffers(page, count) })
            .sFlatMap { pagedResponseOpt ->
                pagedResponseOpt.sIfPresentOrDefault(
                    { pager ->
                        if (shopId != null) {
                            updatePreOrdersCounter(
                                shopId, pager.items, shopsDataSource, Dispatchers.IO
                            )
                        }
                        Result.Success(pager)
                    },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getNoveltyOffers(
        page: Int?,
        count: Int?,
        shopId: Int?
    ): Result<PagedResponse<BaseOfferResponse>> {
        return callApi(call = { apiV1.getNoveltyOffers(page, count) })
            .sFlatMap { pagedResponseOpt ->
                pagedResponseOpt.sIfPresentOrDefault(
                    { pager ->
                        if (shopId != null) {
                            updatePreOrdersCounter(
                                shopId, pager.items, shopsDataSource, Dispatchers.IO
                            )
                        }
                        Result.Success(pager)
                    },
                    { Result.Failure(ApiException()) })
            }
    }

    private fun notifyProfileChanges(snapshot: ProfilePrefs) {
        val changes = mutableListOf<ProfileBusEvent.Change>()
        if (snapshot.firstName != consumerPreferences.profilePrefs.firstName) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.FIRST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.firstName)
            )
            changes.add(change)
        }
        if (snapshot.patronymic != consumerPreferences.profilePrefs.patronymic) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PATRONYMIC,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.patronymic)
            )
            changes.add(change)
        }
        if (snapshot.lastName != consumerPreferences.profilePrefs.lastName) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.LAST_NAME,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.lastName)
            )
            changes.add(change)
        }
        if (snapshot.city != consumerPreferences.profilePrefs.city) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.CITY,
                ProfileBusEvent.FieldValue.CityObjectValue(consumerPreferences.profilePrefs.city)
            )
            changes.add(change)
        }
        if (snapshot.phone != consumerPreferences.profilePrefs.phone) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.PHONE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.phone)
            )
            changes.add(change)
        }
        if (snapshot.email != consumerPreferences.profilePrefs.email) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.EMAIL,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.email)
            )
            changes.add(change)
        }
        if (snapshot.gender != consumerPreferences.profilePrefs.gender) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.GENDER,
                ProfileBusEvent.FieldValue.GenderObjectValue(consumerPreferences.profilePrefs.gender)
            )
            changes.add(change)
        }
        if (snapshot.birthDate != consumerPreferences.profilePrefs.birthDate) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BIRTH_DATE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.birthDate)
            )
            changes.add(change)
        }
        if (snapshot.balance != consumerPreferences.profilePrefs.balance) {
            if (snapshot.balance != null) {
                sendBroadcastMessage(app, Constants.RECEIVER_ACTION_SOUND_BONUSES)
            }
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BALANCE,
                ProfileBusEvent.FieldValue.LongValue(consumerPreferences.profilePrefs.balance)
            )
            changes.add(change)
        }
        if (snapshot.moneyAmount != consumerPreferences.profilePrefs.moneyAmount) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.MONEY_AMOUNT,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.moneyAmount)
            )
            changes.add(change)
        }
        if (snapshot.qrCode != consumerPreferences.profilePrefs.qrCode) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.QR_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.qrCode)
            )
            changes.add(change)
        }
        if (snapshot.inviteCode != consumerPreferences.profilePrefs.inviteCode) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.INVITE_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.inviteCode)
            )
            changes.add(change)
        }
        if (snapshot.referralCode != consumerPreferences.profilePrefs.referralCode) {
            val change = ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.REFERRAL_CODE,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.referralCode)
            )
            changes.add(change)
        }
        CoreBusEventsFactory.profileChanges(changes)
    }

    override suspend fun sendValidationCode(): Result<DataResponse<Any>> {
        return callApi(call = { apiV2.sendValidationCode() })
            .flatMap { dataResponseOpt ->
                dataResponseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun deleteProfile(request: DeleteProfileRequest): Result<DataResponse<Any>> {
        return callApi(call = { apiV2.deleteProfile(request = request) })
            .flatMap { dataResponseOpt ->
                dataResponseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }
}