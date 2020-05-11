package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.OrdersApiV1
import ua.com.wl.dlp.data.api.OrdersApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.orders.order.rate.RateOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.PreOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.order.pre_order.GeneralPreOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.orders.order.*
import ua.com.wl.dlp.data.api.responses.orders.order.rate.OrderRateResponse
import ua.com.wl.dlp.data.api.responses.orders.order.rate.BaseOrderRateResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.PreOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.BasePreOrderResponse
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationItemResponse
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationDetailedResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.api.orders.order.GeneralPreOrderException
import ua.com.wl.dlp.domain.interactors.OrdersInteractor

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
    ): Result<PagedResponse<BaseOrderResponse>> {
        return callApi(call = { apiV2.getOrders(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getOrder(orderId: Int): Result<OrderResponse> {
        return callApi(call = { apiV2.getOrder(orderId) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun rateOrder(orderId: Int, request: RateOrderRequest): Result<BaseOrderRateResponse> {
        return callApi(call = { apiV1.rateOrder(orderId, request) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
            .sOnSuccess { response ->
                withContext(Dispatchers.Main.immediate) {
                    response.order?.let { order ->
                        CoreBusEventsFactory.orderRate(
                            order.shop.id, order.id, response.value)
                    }
                }
            }
    }

    override suspend fun getOrderRate(orderId: Int): Result<OrderRateResponse> {
        return callApi(call = { apiV1.getOrderRate(orderId) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getLastOrderRate(): Result<OrderRateResponse> {
        return callApi(call = { apiV1.getLastOrderRate() })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun createPreOrder(request: PreOrderRequest): Result<PreOrderResponse> {
        return callApi(call = { apiV2.createPreOrder(request) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun createGeneralPreOrder(request: GeneralPreOrderRequest): Result<CollectionResponse<PreOrderResponse>> {
        return callApi(
            call = { apiV2.createGeneralPreOrder(request) },
            errorClass = GeneralPreOrderException::class
        ).flatMap { responseOpt ->
            responseOpt.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }
    }

    override suspend fun getPreOrders(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BasePreOrderResponse>> {
        return callApi(call = { apiV2.getPreOrders(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse> {
        return callApi(call = { apiV2.getPreOrder(preOrderId) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun createTableReservation(request: TableReservationRequest): Result<TableReservationItemResponse> {
        return callApi(call = { apiV1.createTableReservation(request) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getTablesReservations(page: Int?, count: Int?): Result<PagedResponse<TableReservationItemResponse>> {
        return callApi(call = { apiV1.getTablesReservations(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getTableReservation(reservationId: Int): Result<TableReservationDetailedResponse> {
        return callApi(call = { apiV1.getTableReservation(reservationId) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun cancelTableReservation(reservationId: Int): Result<Boolean> {
        return callApi(call = { apiV1.cancelTableReservation(reservationId) })
            .map { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { it.isSuccessfully() },
                    { false })
            }
    }
}