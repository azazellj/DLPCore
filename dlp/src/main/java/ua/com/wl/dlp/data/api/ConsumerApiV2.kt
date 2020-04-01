package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.*

import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.InvitationRequest
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.consumer.groups.GroupResponse
import ua.com.wl.dlp.data.api.responses.consumer.coupons.CouponResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.InvitationResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse

/**
 * @author Denis Makovskyi
 */

interface ConsumerApiV2 {

    @GET("api/mobile/v2/consumer/profile/")
    suspend fun getProfile(): Response<DataResponse<ProfileResponse>>

    @PATCH("api/mobile/v2/consumer/profile/")
    suspend fun updateProfile(@Body request: ProfileRequest): Response<DataResponse<ProfileResponse>>

    @GET("api/mobile/v2/consumer/group/")
    suspend fun getGroups(): Response<CollectionResponse<GroupResponse>>

    @GET("api/mobile/v2/coupon/")
    suspend fun getCoupons(): Response<DataResponse<PagedResponse<CouponResponse>>>

    @GET("api/mobile/v2/coupon/{coupon_id}/")
    suspend fun getCoupon(@Path("coupon_id") id: Int): Response<DataResponse<CouponResponse>>

    @GET("api/mobile/v2/consumer/qr-code/")
    suspend fun getQrCode(): Response<DataResponse<QrCodeResponse>>

    @POST("api/mobile/v2/consumer/refer-lead/")
    suspend fun useInviteCode(@Body request: InvitationRequest): Response<DataResponse<InvitationResponse>>

    @GET("api/mobile/v2/consumer/balance/history/")
    suspend fun getTransactionsHistory(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<DataResponse<PagedResponse<TransactionResponse>>>
}