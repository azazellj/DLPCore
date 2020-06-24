package ua.com.wl.dlp.domain.interactors

import java.util.*

import ua.com.wl.archetype.utils.Optional

import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.InvitationRequest
import ua.com.wl.dlp.data.api.requests.consumer.history.notifications.NotificationsReadRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.consumer.ranks.RankResponse
import ua.com.wl.dlp.data.api.responses.consumer.groups.GroupResponse
import ua.com.wl.dlp.data.api.responses.consumer.info.BusinessResponse
import ua.com.wl.dlp.data.api.responses.consumer.coupons.CouponResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.InvitationResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.transactions.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.notifications.NotificationsResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface ConsumerInteractor : OffersInteractor {

    suspend fun getInfo(): Result<BusinessResponse>

    suspend fun getProfile(): Result<ProfileResponse>

    suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse>

    suspend fun getRanks(language: String = Locale.getDefault().language): Result<PagedResponse<RankResponse>>

    suspend fun getRank(rankId: Int, language: String = Locale.getDefault().language): Result<RankResponse>

    suspend fun getCurrentRank(language: String = Locale.getDefault().language): Result<Optional<RankResponse>>

    suspend fun getGroups(): Result<CollectionResponse<GroupResponse>>

    suspend fun getCoupons(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<CouponResponse>>

    suspend fun getCoupon(id: Int): Result<CouponResponse>

    suspend fun getQrCode(): Result<QrCodeResponse>

    suspend fun activateInviteCode(request: InvitationRequest): Result<InvitationResponse>

    suspend fun markNotificationsAsRead(request: NotificationsReadRequest): Result<Boolean>

    suspend fun feedback(
        phone: String? = null,
        email: String? = null,
        message: String,
        callback: Boolean,
        appVersion: String
    ): Result<FeedbackResponse>

    suspend fun getTransactionsHistory(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<TransactionResponse>>

    suspend fun getNotificationsHistory(
        page: Int? = null,
        count: Int? = null
    ): Result<NotificationsResponse>

    @Deprecated(message = "This method will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
    suspend fun getPromoOffers(
        page: Int? = null, 
        count: Int? = null,
        shopId: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getNoveltyOffers(
        page: Int? = null, 
        count: Int? = null,
        shopId: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>
}