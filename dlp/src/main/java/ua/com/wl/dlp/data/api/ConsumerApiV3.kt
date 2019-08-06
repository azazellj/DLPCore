package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.requests.consumer.referral.ReferralActivationRequest
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.PaginationResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.ReferralActivationResponse

/**
 * @author Denis Makovskyi
 */

interface ConsumerApiV3 {

    @GET("api/v3/consumer/profile/")
    suspend fun getProfile(): Response<DataResponse<ProfileResponse>>

    @PATCH("api/v3/consumer/profile/")
    suspend fun updateProfile(@Body request: ProfileRequest): Response<DataResponse<ProfileResponse>>

    @POST("api/v3/consumer/refer-lead/")
    suspend fun activateReferralCode(@Body request: ReferralActivationRequest): Response<DataResponse<ReferralActivationResponse>>

    @GET("api/v3/consumer/balance/history/")
    suspend fun loadTransactionsHistory(): Response<DataResponse<PaginationResponse<TransactionResponse>>>
}