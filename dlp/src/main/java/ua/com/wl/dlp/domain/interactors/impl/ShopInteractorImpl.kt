package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.ShopApiV1
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.shop.order.PreOrderCreationRequest
import ua.com.wl.dlp.data.api.requests.shop.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.order.BasePreOrderResponse
import ua.com.wl.dlp.data.api.responses.shop.order.PreOrderCreationResponse
import ua.com.wl.dlp.data.api.responses.shop.order.PreOrderResponse
import ua.com.wl.dlp.data.api.responses.shop.table.TableReservationResponse
import ua.com.wl.dlp.data.db.DbErrorKeys
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.db.DatabaseException
import ua.com.wl.dlp.domain.exeptions.db.DbQueryException
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.utils.only
import ua.com.wl.dlp.utils.toOfferEntity

/**
 * @author Denis Makovskyi
 */

class ShopInteractorImpl(
    errorsMapper: ErrorsMapper,
    private val apiV1: ShopApiV1,
    private val shopsDataSource: ShopsDataSource
) : ShopInteractor, UseCase(errorsMapper) {

    override suspend fun getCityShops(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<CityShopsResponse>> =
        callApi(call = { apiV1.getCityShops(page, count) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getShop(shopId: Int): Result<ShopResponse> =
        callApi(call = { apiV1.getShop(shopId) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getPromoOffers(
        shopId: Int,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BaseOfferResponse>> =
        callApi(call = { apiV1.getShopPromoOffers(shopId, page, count) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getFavouriteOffers(
        shopId: Int,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BaseOfferResponse>> =
        callApi(call = { apiV1.getShopFavouriteOffers(shopId, page, count) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun addOfferToFavourites(offerId: Int): Result<Boolean> =
        callApi(call = { apiV1.addShopOfferToFavourite(offerId) })
            .map { response ->
                response.ifPresentOrDefault(
                    { it.isSuccessfully() },
                    { false })
            }.sOnSuccess { isSuccess ->
                if (isSuccess) {
                    withContext(Dispatchers.Main.immediate) {
                        CoreBusEventsFactory.offerFavouriteStatus(
                            offerId = offerId,
                            isFavourite = true
                        )
                    }
                }
            }

    override suspend fun removeOfferFromFavourites(offerId: Int): Result<Boolean> =
        callApi(call = { apiV1.removeShopOfferFromFavourite(offerId) })
            .map { response ->
                response.ifPresentOrDefault(
                    { it.isSuccessfully() },
                    { false })
            }.sOnSuccess { isSuccess ->
                if (isSuccess) {
                    withContext(Dispatchers.Main.immediate) {
                        CoreBusEventsFactory.offerFavouriteStatus(
                            offerId = offerId,
                            isFavourite = false
                        )
                    }
                }
            }

    override suspend fun getOffer(offerId: Int): Result<OfferResponse> =
        callApi(call = { apiV1.getShopOffer(offerId) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun saveShopInDb(shop: ShopEntity): Result<Boolean> =
        callQuery(call = { shopsDataSource.upsertShop(shop) })
            .sOnSuccess { isSuccess ->
                if (isSuccess) {
                    val selectOffersQueryRes = callQuery(call = { shopsDataSource.getShopOffers(shop.id) })
                    if (selectOffersQueryRes is Result.Success) {
                        selectOffersQueryRes.data.only { offers ->
                            val price = offers.sumByDouble { offer ->
                                offer.priceInCurrency?.toDouble()?.times(offer.preOrderCount) ?: 0.0
                            }
                            CoreBusEventsFactory.ordersPrice(shop.id, offers.size, price)
                        }
                    }
                }
            }

    override suspend fun deleteShopFromDb(shop: ShopEntity): Result<Boolean> =
        callQuery(call = { shopsDataSource.deleteShop(shop) })
            .sOnEach {
                withContext(Dispatchers.Main.immediate) {
                    CoreBusEventsFactory.ordersPrice(shop.id)
                    CoreBusEventsFactory.orderCounter(resetAll = true)
                }
            }

    override suspend fun incrementPreOrderCounter(
        shopId: Int,
        offer: BaseOfferResponse
    ): Result<OfferEntity> =
        callQuery(call = { shopsDataSource.getShop(shopId) })
            .sFlatMap { shopOptional ->
                shopOptional.sIfPresentOrDefault(
                    {
                        Result.Success(it) as Result<ShopEntity>
                    },
                    {
                        val shopEntity = ShopEntity(shopId)
                        when(val upsertShopQueryRes = callQuery(call = { shopsDataSource.upsertShop(shopEntity) })) {
                            is Result.Success -> {
                                if (upsertShopQueryRes.data) {
                                    Result.Success(shopEntity)
                                } else {
                                    Result.Failure(DbQueryException(DbErrorKeys.QUERY_ERROR))
                                }
                            }
                            is Result.Failure -> upsertShopQueryRes
                        }
                    })
            }.sFlatMap { shop ->
                when(val selectOfferQueryRes = callQuery(call = { shopsDataSource.getOffer(requireNotNull(offer.id)) })) {
                    is Result.Success -> {
                        val offerEntity = selectOfferQueryRes.data.ifPresentOrDefault(
                            { offer.toOfferEntity(shop.id, it.preOrderCount.inc()) },
                            { offer.toOfferEntity(shop.id, 1) })
                        when(val upsertOfferQueryRes = callQuery(call = { shopsDataSource.upsertOffer(offerEntity) })) {
                            is Result.Success -> {
                                if (upsertOfferQueryRes.data) {
                                    Result.Success(offerEntity)
                                } else {
                                    Result.Failure(DbQueryException(DbErrorKeys.QUERY_ERROR))
                                }
                            }
                            is Result.Failure -> upsertOfferQueryRes
                        }
                    }
                    is Result.Failure -> selectOfferQueryRes
                }
            }.sOnSuccess { offerEntity ->
                CoreBusEventsFactory.orderCounter(
                    offerEntity.tradeItem, offerEntity.preOrderCount
                )
                //-
                val selectOffersQueryRes = callQuery(call = { shopsDataSource.getShopOffers(shopId) })
                if (selectOffersQueryRes is Result.Success) {
                    selectOffersQueryRes.data.only { offers ->
                        val price = offers.sumByDouble { offer ->
                            offer.priceInCurrency?.toDouble()?.times(offer.preOrderCount) ?: 0.0
                        }
                        CoreBusEventsFactory.ordersPrice(shopId, offers.size, price)
                    }
                }
            }

    override suspend fun decrementPreOrderCounter(
        shopId: Int,
        offer: BaseOfferResponse
    ): Result<OfferEntity> =
        callQuery(call = { shopsDataSource.getShop(shopId) })
            .sFlatMap { shopOptional ->
                shopOptional.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)) })
            }.sFlatMap { shop ->
                when(val selectOfferQueryRes = callQuery(call = { shopsDataSource.getOffer(requireNotNull(offer.id)) })) {
                    is Result.Success -> {
                        selectOfferQueryRes.data.sIfPresentOrDefault(
                            {
                                val offerEntity = offer.toOfferEntity(shop.id, it.preOrderCount)
                                if (offerEntity.preOrderCount > 0) {
                                    offerEntity.preOrderCount = offerEntity.preOrderCount.dec()
                                }
                                if (offerEntity.preOrderCount > 0) {
                                    when(val upsertOfferQueryRes = callQuery(call = { shopsDataSource.upsertOffer(offerEntity) })) {
                                        is Result.Success -> Result.Success(offerEntity)
                                        is Result.Failure -> upsertOfferQueryRes
                                    }

                                } else {
                                    when(val deleteOfferQueryRes = callQuery(call = { shopsDataSource.deleteOffer(offerEntity.id) })) {
                                        is Result.Success -> Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE))
                                        is Result.Failure -> deleteOfferQueryRes
                                    }
                                }
                            },
                            {
                                Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS))
                            })
                    }
                    is Result.Failure -> selectOfferQueryRes
                }
            }.sOnSuccess { offerEntity ->
                CoreBusEventsFactory.orderCounter(
                    offerEntity.tradeItem, offerEntity.preOrderCount)
                //-
                val selectOffersQueryRes = callQuery(call = { shopsDataSource.getShopOffers(shopId) })
                if (selectOffersQueryRes is Result.Success) {
                    selectOffersQueryRes.data.only { offers ->
                        val price = offers.sumByDouble { offer ->
                            offer.priceInCurrency?.toDouble()?.times(offer.preOrderCount) ?: 0.0
                        }
                        CoreBusEventsFactory.ordersPrice(shopId, offers.size, price)
                    }
                }
            }.sOnFailure { error ->
                if (error.message == DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE) {
                    CoreBusEventsFactory.orderCounter(offer.tradeItem, 0)
                }
            }

    override suspend fun getShopPreOrders(shopId: Int): Result<List<OfferEntity>> =
        callQuery(call = { shopsDataSource.getShopOffers(shopId) })

    override suspend fun createPreOrder(request: PreOrderCreationRequest): Result<PreOrderCreationResponse> =
        callApi(call = { apiV1.createPreOrder(request) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun getPreOrders(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BasePreOrderResponse>> =
        callApi(call = { apiV1.getPreOrders(page, count) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse> =
        callApi(call = { apiV1.getPreOrder(preOrderId) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun createTableReservation(request: TableReservationRequest): Result<TableReservationResponse> =
        callApi(call = { apiV1.createTableReservation(request) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun getTablesReservations(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<TableReservationResponse>> =
        callApi(call = { apiV1.getTablesReservations(page, count) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun getTableReservation(reservationId: Int): Result<TableReservationResponse> =
        callApi(call = { apiV1.getTableReservation(reservationId) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun cancelTableReservation(reservationId: Int): Result<Boolean> =
        callApi(call = { apiV1.cancelTableReservation(reservationId) }).map { response ->
            response.ifPresentOrDefault(
                { it.isSuccessfully() },
                { false })
        }
}