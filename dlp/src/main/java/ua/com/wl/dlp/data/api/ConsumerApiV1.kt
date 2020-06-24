package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.*

import ua.com.wl.dlp.data.api.requests.consumer.feedback.FeedbackRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.ranks.RankResponse
import ua.com.wl.dlp.data.api.responses.consumer.feedback.FeedbackResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse

/**
 * @author Denis Makovskyi
 */

@Deprecated(message = "Needs further refactoring and merging with api/mobile/v2")
interface ConsumerApiV1 {

    @GET("api/mobile/v1/consumer/ranks/language/{locale}/")
    suspend fun getRanks(@Path("locale") locale: String): Response<PagedResponse<RankResponse>>

    @GET("api/mobile/v1/consumer/ranks/{id}/language/{locale}/")
    suspend fun getRank(
        @Path("id") rankId: Int,
        @Path("locale") locale: String
    ): Response<RankResponse>

    @POST("api/mobile/v1/consumer/feedback/")
    suspend fun feedback(@Body request: FeedbackRequest): Response<FeedbackResponse>

    @Deprecated(message = "This method will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
    @GET("api/mobile/v1/consumer/promo-offers/")
    suspend fun getPromoOffers(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BaseOfferResponse>>

    @GET("api/mobile/v1/consumer/novelty-offers/")
    suspend fun getNoveltyOffers(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BaseOfferResponse>>
}