package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

import ua.com.wl.dlp.data.api.requests.consumer.feedback.FeedbackRequest
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.QrCodeResponse

/**
 * @author Denis Makovskyi
 */

@Deprecated(message = "Needs further refactoring and merging with api/v3")
interface ConsumerApiV1 {

    @GET("api/v1/consumer/qr-code/")
    suspend fun getQrCode(): Response<QrCodeResponse>

    @POST("api/v1/consumer/feedback/")
    suspend fun feedback(@Body request: FeedbackRequest): Response<FeedbackResponse>
}