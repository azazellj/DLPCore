package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.chain.StoreChainResponse
import ua.com.wl.dlp.data.api.responses.shop.rubric.RubricResponse

/**
 * @author Denis Makovskyi
 */

interface ShopApiV2 {

    @GET("api/mobile/v2/rubric/consumer/shops/{shop_id}/")
    suspend fun getRubrics(
        @Path("shop_id") shopId: Int,
        @Query("language") language: String
    ): Response<DataResponse<CollectionResponse<RubricResponse>>>


    @GET("/api/mobile/v2/store-chain/")
    suspend fun getStoreChain(
        @Query("language") language: String
    ): Response<DataResponse<CollectionResponse<StoreChainResponse>>>

    @GET("/api/mobile/v2/shop/city/")
    suspend fun getShopWithChain(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null,
        @Query("language") language: String
    ): Response<DataResponse<PagedResponse<CityShopsResponse>>>
}