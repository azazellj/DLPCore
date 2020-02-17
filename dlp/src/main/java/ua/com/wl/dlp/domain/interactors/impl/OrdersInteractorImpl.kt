package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.OrdersApiV1
import ua.com.wl.dlp.data.api.OrdersApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.orders.order.GeneralPreOrderCreationRequest
import ua.com.wl.dlp.data.api.requests.orders.order.PreOrderCreationRequest
import ua.com.wl.dlp.data.api.requests.orders.order.RateOrderRequest
import ua.com.wl.dlp.data.api.requests.orders.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.models.orders.order.GeneralPreOrderItem
import ua.com.wl.dlp.data.api.responses.orders.order.*
import ua.com.wl.dlp.data.api.responses.orders.table.TableReservationResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.api.orders.order.GeneralPreOrderException
import ua.com.wl.dlp.domain.interactors.OrdersInteractor

/**
 * @author Denis Makovskyi
 */

class OrdersInteractorImpl constructor(
    errorsMapper: ErrorsMapper,
    private val apiV1: OrdersApiV1,
    private val apiV2: OrdersApiV2
) : UseCase(errorsMapper), OrdersInteractor {

    override suspend fun getOrders(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<OrderSimpleResponse>> {
        return callApi(call = { apiV1.getOrders(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getOrder(orderId: Int): Result<OrderResponse> {
        return callApi(call = { apiV1.getOrder(orderId) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun rateOrder(
        orderId: Int,
        value: Int,
        comment: String
    ): Result<BaseOrderRateResponse> {
        return callApi(call = { apiV1.rateOrder(orderId, RateOrderRequest(value, comment)) })
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

    override suspend fun createPreOrder(request: PreOrderCreationRequest): Result<PreOrderCreationResponse> {
        return callApi(call = { apiV1.createPreOrder(request) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun createGeneralPreOrder(request: GeneralPreOrderCreationRequest): Result<CollectionResponse<GeneralPreOrderItem>> {
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
        return callApi(call = { apiV1.getPreOrders(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse> {
        return callApi(call = { apiV1.getPreOrder(preOrderId) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun createTableReservation(request: TableReservationRequest): Result<TableReservationResponse> {
        return callApi(call = { apiV1.createTableReservation(request) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getTablesReservations(page: Int?, count: Int?): Result<PagedResponse<TableReservationResponse>> {
        return callApi(call = { apiV1.getTablesReservations(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getTableReservation(reservationId: Int): Result<TableReservationResponse> {
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