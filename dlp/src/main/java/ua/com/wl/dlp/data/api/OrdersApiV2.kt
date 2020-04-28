package ua.com.wl.dlp.data.api

import retrofit2.http.*
import retrofit2.Response

import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.PreOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.GeneralPreOrderRequest
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.orders.order.OrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.BaseOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.PreOrderCreationResponse
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.GeneralPreOrderItem

/**
 * @author Denis Makovskyi
 */

interface OrdersApiV2 {

    @GET("api/mobile/v2/order/")
    suspend fun getOrders(
        @Query("page") page: Int? = null,
        @Query("page_size") count: Int? = null
    ): Response<DataResponse<PagedResponse<BaseOrderResponse>>>

    @GET("api/mobile/v2/order/{order_id}/")
    suspend fun getOrder(@Path("order_id") orderId: Int): Response<DataResponse<OrderResponse>>

    @POST("api/mobile/v2/pre-order/")
    suspend fun createPreOrder(@Body request: PreOrderRequest): Response<PreOrderCreationResponse>

    @POST("api/mobile/v2/pre-order/basket/")
    suspend fun createGeneralPreOrder(@Body request: GeneralPreOrderRequest): Response<CollectionResponse<GeneralPreOrderItem>>
}