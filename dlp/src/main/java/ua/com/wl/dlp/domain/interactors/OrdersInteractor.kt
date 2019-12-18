package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.requests.orders.order.PreOrderCreationRequest
import ua.com.wl.dlp.data.api.requests.orders.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.orders.order.*
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface OrdersInteractor {

    suspend fun getOrders(page: Int? = null, count: Int? = null): Result<PagedResponse<OrderSimpleResponse>>

    suspend fun getOrder(orderId: Int): Result<OrderResponse>

    suspend fun rateOrder(orderId: Int, value: Int, comment: String = ""): Result<BaseOrderRateResponse>

    suspend fun getOrderRate(orderId: Int): Result<OrderRateResponse>

    suspend fun getLastOrderRate(): Result<OrderRateResponse>

    suspend fun createPreOrder(request: PreOrderCreationRequest): Result<PreOrderCreationResponse>

    suspend fun getPreOrders(page: Int? = null, count: Int? = null): Result<PagedResponse<BasePreOrderResponse>>

    suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse>

    suspend fun createTableReservation(request: TableReservationRequest): Result<TableReservationResponse>

    suspend fun getTablesReservations(page: Int? = null, count: Int? = null): Result<PagedResponse<TableReservationResponse>>

    suspend fun getTableReservation(reservationId: Int): Result<TableReservationResponse>

    suspend fun cancelTableReservation(reservationId: Int): Result<Boolean>
}