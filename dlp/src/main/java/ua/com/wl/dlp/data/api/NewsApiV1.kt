package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ua.com.wl.dlp.data.api.responses.consumer.history.transactions.BalanceChangeResponse
import ua.com.wl.dlp.data.api.responses.news.ArticleResponse
import ua.com.wl.dlp.data.api.responses.news.BaseArticlePagedResponse

/**
 * @author Denis Makovskyi
 */

@Deprecated(message = "Needs further refactoring and merging with api/mobile/v2")
interface NewsApiV1 {

    @GET("api/mobile/v1/consumer/news/feed/")
    suspend fun getCityNewsFeed(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<BaseArticlePagedResponse>

    @GET("api/mobile/v1/consumer/news/feed/shop/{shop_id}/")
    suspend fun getShopNewsFeed(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<BaseArticlePagedResponse>

    @GET("api/mobile/v1/consumer/news/{item_id}/")
    suspend fun getArticle(@Path("item_id") id: Int): Response<ArticleResponse>

    @POST("api/mobile/v1/consumer/news/{item_id}/view/")
    suspend fun collectBonusesPerView(@Path("item_id") id: Int): Response<BalanceChangeResponse>
}