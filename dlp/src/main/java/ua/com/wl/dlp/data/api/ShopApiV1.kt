package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopNewsItemResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse

/**
 * @author Denis Makovskyi
 */

@Deprecated(message = "Needs further refactoring for api/v3")
interface ShopApiV1 {

    @GET("api/v1/consumer/city-shops/")
    suspend fun getCityShops(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null

    ): Response<PagedResponse<CityShopsResponse>>

    @GET("api/v1/consumer/news/feed/shop/{shop_id}/")
    suspend fun getShopNewsFeed(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null

    ): Response<PagedResponse<ShopNewsItemResponse>>

    @GET("api/v1/consumer/shops/{shop_id}/promo-offers/")
    suspend fun getShopPromoOffers(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null

    ): Response<PagedResponse<ShopOfferResponse>>
}