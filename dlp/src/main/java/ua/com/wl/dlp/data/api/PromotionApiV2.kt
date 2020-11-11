package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.promotion.PromotionsResponse

interface PromotionApiV2 {

    @GET("/api/mobile/v2/promotion/")
    suspend fun getPromotions(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null,
        @Query("language") language: String
    ): Response<DataResponse<PagedResponse<PromotionsResponse>>>

    @GET("/api/mobile/v2/promotion/{promotion_id}/")
    suspend fun getPromotionDetail(
        @Path("promotion_id") promotionId: Int,
        @Query("language") language: String
    ): Response<PromotionsResponse>
}