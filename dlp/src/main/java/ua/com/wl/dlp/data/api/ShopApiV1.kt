package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.*

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse

/**
 * @author Denis Makovskyi
 */

@Deprecated(message = "Needs further refactoring for api/mobile/v2")
interface ShopApiV1 {

    @GET("api/mobile/v1/consumer/city-shops/")
    suspend fun getCityShops(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<CityShopsResponse>>

    @GET("api/mobile/v1/consumer/shops/{shop_id}/")
    suspend fun getShop(@Path("shop_id") shopId: Int): Response<ShopResponse>

    @GET("api/mobile/v1/consumer/offers/feed/shop/{shop_id}/")
    suspend fun getOffers(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null,
        @Query("rubric_ids") rubricId: String? = null
    ): Response<PagedResponse<BaseOfferResponse>>

    @GET("api/mobile/v1/consumer/shops/{shop_id}/promo-offers/")
    suspend fun getPromoOffers(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BaseOfferResponse>>

    @GET("api/mobile/v1/consumer/shops/{shop_id}/novelty-offers/")
    suspend fun getNoveltyOffers(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BaseOfferResponse>>

    @GET("api/mobile/v1/consumer/favorite-offers/shop/{shop_id}/")
    suspend fun getFavouriteOffers(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BaseOfferResponse>>
}