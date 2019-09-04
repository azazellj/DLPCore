package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.news.BaseNewsItemResponse
import ua.com.wl.dlp.data.api.responses.news.NewsItemResponse

/**
 * @author Denis Makovskyi
 */

@Deprecated(message = "Needs further refactoring and merging with api/v3")
interface NewsApiV1 {

    @GET("api/v1/consumer/news/feed/")
    suspend fun getCityNewsFeed(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null

    ): Response<PagedResponse<BaseNewsItemResponse>>

    @GET("api/v1/consumer/news/feed/shop/{shop_id}/")
    suspend fun getShopNewsFeed(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null

    ): Response<PagedResponse<BaseNewsItemResponse>>

    @GET("api/v1/consumer/news/{item_id}/")
    suspend fun getNewsItem(@Path("item_id")id: Int): Response<NewsItemResponse>

}