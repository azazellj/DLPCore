package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.InvitationResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface ConsumerInteractor : OffersInteractor {

    suspend fun getProfile(): Result<ProfileResponse>

    suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse>

    suspend fun getQrCode(): Result<QrCodeResponse>

    suspend fun useInviteCode(code: String): Result<InvitationResponse>

    suspend fun getPromoOffers(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getNoveltyOffers(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getTransactionsHistory(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<TransactionResponse>>

    suspend fun feedback(
        message: String,
        appVersion: String,
        callback: Boolean,
        phone: String? = null,
        email: String? = null
    ): Result<FeedbackResponse>
}