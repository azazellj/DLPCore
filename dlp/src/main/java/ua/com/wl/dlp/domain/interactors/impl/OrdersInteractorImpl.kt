package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.com.wl.dlp.data.api.OrdersApiV1
import ua.com.wl.dlp.data.api.OrdersApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.GeneralPreOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.SinglePreOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.order.rate.RateOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.orders.order.OrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.GeneralPreOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.PreOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.rate.BaseOrderRateResponse
import ua.com.wl.dlp.data.api.responses.orders.order.rate.OrderRateResponse
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationDetailedResponse
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationItemResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.orders.order.GeneralPreOrderException
import ua.com.wl.dlp.domain.interactors.OrdersInteractor
import ua.com.wl.dlp.utils.fromDataResponse
import ua.com.wl.dlp.utils.fromResponse
import ua.com.wl.dlp.utils.mapIsSuccessfully

/**
 * @author Denis Makovskyi
 */

class OrdersInteractorImpl(
    errorsMapper: ErrorsMapper,
    private val apiV1: OrdersApiV1,
    private val apiV2: OrdersApiV2
) : UseCase(errorsMapper), OrdersInteractor {

    override suspend fun getOrders(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<OrderResponse>> {
        return callApi(call = { apiV2.getOrders(page, count) })
            .fromDataResponse()
    }

    override suspend fun getOrder(orderId: Int): Result<OrderResponse> {
        return callApi(call = { apiV2.getOrder(orderId) })
            .fromDataResponse()
    }

    override suspend fun rateOrder(
        orderId: Int,
        request: RateOrderRequest
    ): Result<BaseOrderRateResponse> {
        return callApi(call = { apiV1.rateOrder(orderId, request) })
            .fromResponse()
            .sOnSuccess { response ->
                withContext(Dispatchers.Main.immediate) {
                    response.order?.let { order ->
                        CoreBusEventsFactory.orderRate(
                            order.shop.id, order.id, response.value
                        )
                    }
                }
            }
    }

    override suspend fun getOrderRate(orderId: Int): Result<OrderRateResponse> {
        return callApi(call = { apiV1.getOrderRate(orderId) })
            .fromResponse()
    }

    override suspend fun getLastOrderRate(): Result<OrderRateResponse> {
        return callApi(call = { apiV1.getLastOrderRate() })
            .fromResponse()
    }

    override suspend fun createPreOrder(request: SinglePreOrderRequest): Result<PreOrderResponse> {
        return callApi(call = { apiV2.createPreOrder(request) })
            .fromDataResponse()
    }

    override suspend fun createGeneralPreOrder(request: GeneralPreOrderRequest): Result<GeneralPreOrderResponse> {
        return callApi(
            call = { apiV2.createGeneralPreOrder(request) },
            errorMapper = { type, cause -> GeneralPreOrderException(type, cause) }
        ).fromDataResponse()
    }

    override suspend fun getPreOrders(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<PreOrderResponse>> {
        return callApi(call = { apiV2.getPreOrders(page, count) })
            .fromDataResponse()
    }

    override suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse> {
        return callApi(call = { apiV2.getPreOrder(preOrderId) })
            .fromDataResponse()
    }

    override suspend fun createTableReservation(request: TableReservationRequest): Result<TableReservationItemResponse> {
        return callApi(call = { apiV1.createTableReservation(request) })
            .fromResponse()
    }

    override suspend fun getTablesReservations(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<TableReservationItemResponse>> {
        return callApi(call = { apiV1.getTablesReservations(page, count) })
            .fromResponse()
    }

    override suspend fun getTableReservation(reservationId: Int): Result<TableReservationDetailedResponse> {
        return callApi(call = { apiV1.getTableReservation(reservationId) })
            .fromResponse()
    }

    override suspend fun cancelTableReservation(reservationId: Int): Result<Boolean> {
        return callApi(call = { apiV1.cancelTableReservation(reservationId) })
            .mapIsSuccessfully()
    }
}