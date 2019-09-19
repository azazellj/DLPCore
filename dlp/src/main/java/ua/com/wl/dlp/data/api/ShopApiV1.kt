package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.*

import ua.com.wl.dlp.data.api.requests.shop.order.PreOrderCreationRequest
import ua.com.wl.dlp.data.api.requests.shop.order.RateOrderRequest
import ua.com.wl.dlp.data.api.requests.shop.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.BaseResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.order.*
import ua.com.wl.dlp.data.api.responses.shop.table.TableReservationResponse

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

    @GET("api/mobile/v1/consumer/shops/{shop_id}/promo-offers/")
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

    @GET("api/mobile/v1/consumer/orders/")
    suspend fun getOrders(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<OrderSimpleResponse>>

    @GET("api/mobile/v1/consumer/orders/{order_id}/")
    suspend fun getOrder(@Path("order_id") orderId: Int): Response<OrderResponse>

    @PATCH("api/mobile/v1/consumer/order-rates/{order_id}/")
    suspend fun rateOrder(
        @Path("order_id") orderId: Int,
        @Body request: RateOrderRequest
    ): Response<BaseOrderRateResponse>

    @GET("api/mobile/v1/consumer/order-rates/{order_id}/")
    suspend fun getOrderRate(@Path("order_id") orderId: Int): Response<OrderRateResponse>

    @GET("api/mobile/v1/consumer/last-order-rate/")
    suspend fun getLastOrderRate(): Response<OrderRateResponse>

    @POST("api/mobile/v1/consumer/pre-orders/")
    suspend fun createPreOrder(@Body request: PreOrderCreationRequest): Response<PreOrderCreationResponse>

    @GET("api/mobile/v1/consumer/pre-orders/")
    suspend fun getPreOrders(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<BasePreOrderResponse>>

    @GET("api/mobile/v1/consumer/pre-orders/{pre_order_id}")
    suspend fun getPreOrder(@Path("pre_order_id") preOrderId: Int): Response<PreOrderResponse>

    @POST("api/mobile/v1/consumer/table-reservations/")
    suspend fun createTableReservation(@Body request: TableReservationRequest): Response<TableReservationResponse>

    @GET("api/mobile/v1/consumer/table-reservations/")
    suspend fun getTablesReservations(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<PagedResponse<TableReservationResponse>>

    @GET("api/mobile/v1/consumer/table-reservation/{reservation_id}/")
    suspend fun getTableReservation(@Path("reservation_id") reservationId: Int): Response<TableReservationResponse>

    @PATCH("api/mobile/v1/consumer/table-reservation/{reservation_id}/reject/")
    suspend fun cancelTableReservation(@Path("reservation_id") reservationId: Int): Response<BaseResponse>
}