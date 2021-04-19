package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.requests.orders.order.rate.RateOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.SinglePreOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.GeneralPreOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.orders.order.*
import ua.com.wl.dlp.data.api.responses.orders.order.rate.OrderRateResponse
import ua.com.wl.dlp.data.api.responses.orders.order.rate.BaseOrderRateResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.PreOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.BasePreOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.GeneralPreOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationItemResponse
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationDetailedResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface OrdersInteractor {

    suspend fun getOrders(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<OrderResponse>>

    suspend fun getOrder(orderId: Int): Result<OrderResponse>

    suspend fun rateOrder(orderId: Int, request: RateOrderRequest): Result<BaseOrderRateResponse>

    suspend fun getOrderRate(orderId: Int): Result<OrderRateResponse>

    suspend fun getLastOrderRate(): Result<OrderRateResponse>

    suspend fun createPreOrder(request: SinglePreOrderRequest): Result<PreOrderResponse>

    suspend fun createGeneralPreOrder(request: GeneralPreOrderRequest): Result<GeneralPreOrderResponse>

    suspend fun getPreOrders(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BasePreOrderResponse>>

    suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse>

    suspend fun createTableReservation(request: TableReservationRequest): Result<TableReservationItemResponse>

    suspend fun getTablesReservations(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<TableReservationItemResponse>>

    suspend fun getTableReservation(reservationId: Int): Result<TableReservationDetailedResponse>

    suspend fun cancelTableReservation(reservationId: Int): Result<Boolean>
}