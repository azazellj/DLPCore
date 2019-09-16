package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.*

import ua.com.wl.dlp.data.api.requests.shop.order.PreOrderCreationRequest
import ua.com.wl.dlp.data.api.requests.shop.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.BaseResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.data.api.responses.shop.order.BasePreOrderResponse
import ua.com.wl.dlp.data.api.responses.shop.order.PreOrderCreationResponse
import ua.com.wl.dlp.data.api.responses.shop.order.PreOrderResponse
import ua.com.wl.dlp.data.api.responses.shop.table.TableReservationResponse

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

    @GET("api/v1/consumer/shops/{shop_id}/")
    suspend fun getShop(@Path("shop_id") shopId: Int): Response<ShopResponse>

    @GET("api/v1/consumer/shops/{shop_id}/promo-offers/")
    suspend fun getShopPromoOffers(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BaseOfferResponse>>

    @GET("api/v1/consumer/favorite-offers/shop/{shop_id}/")
    suspend fun getShopFavouriteOffers(
        @Path("shop_id") shopId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BaseOfferResponse>>

    @POST("api/v1/consumer/favorite-offers/{offer_id}/")
    suspend fun addShopOfferToFavourite(@Path("offer_id") offerId: Int): Response<BaseResponse>

    @DELETE("api/v1/consumer/favorite-offers/{offer_id}/")
    suspend fun removeShopOfferFromFavourite(@Path("offer_id") offerId: Int): Response<BaseResponse>

    @GET("api/v1/consumer/offers/{offer_id}/")
    suspend fun getShopOffer(@Path("offer_id") offerId: Int): Response<OfferResponse>

    @POST("api/v1/consumer/pre-orders/")
    suspend fun createPreOrder(@Body request: PreOrderCreationRequest): Response<PreOrderCreationResponse>

    @GET("api/v1/consumer/pre-orders/")
    suspend fun getPreOrders(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BasePreOrderResponse>>

    @GET("api/v1/consumer/pre-orders/{pre_order_id}")
    suspend fun getPreOrder(@Path("pre_order_id") preOrderId: Int): Response<PreOrderResponse>

    @POST("api/v1/consumer/table-reservations/")
    suspend fun createTableReservation(@Body request: TableReservationRequest): Response<TableReservationResponse>

    @GET("api/v1/consumer/table-reservations/")
    suspend fun getTablesReservations(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<TableReservationResponse>>

    @GET("api/v1/consumer/table-reservation/{reservation_id}/")
    suspend fun getTableReservation(@Path("reservation_id") reservationId: Int): Response<TableReservationResponse>

    @PATCH("api/v1/consumer/table-reservation/{reservation_id}/reject/")
    suspend fun cancelTableReservation(@Path("reservation_id") reservationId: Int): Response<BaseResponse>
}