package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.api.ShopApiV1
import ua.com.wl.dlp.data.api.ShopApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.chain.ShopChainResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.data.api.responses.shop.rubric.RubricResponse
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
import ua.com.wl.dlp.domain.interactors.OffersInteractor
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.utils.*

/**
 * @author Denis Makovskyi
 */

class ShopInteractorImpl(
    errorsMapper: ErrorsMapper,
    private val apiV1: ShopApiV1,
    private val apiV2: ShopApiV2,
    private val shopsDataSource: ShopsDataSource,
    private val offersInteractor: OffersInteractor
) : UseCase(errorsMapper), ShopInteractor, OffersInteractor by offersInteractor {

    override suspend fun getCityShops(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<CityShopsResponse>> {
        return callApi(call = { apiV1.getCityShops(page, count) })
            .fromResponse()
    }

    override suspend fun getShopsWithChain(
        page: Int?,
        count: Int?,
        language: String
    ): Result<PagedResponse<CityShopsResponse>> {
        return callApi(call = { apiV2.getShopsWithChain(page, count, language) })
            .fromResponse()
    }

    override suspend fun getShop(shopId: Int): Result<ShopResponse> {
        return callApi(call = { apiV1.getShop(shopId) })
            .fromResponse()
    }

    override suspend fun getShopV2(shopId: Int): Result<ShopResponse> {
        return callApi(call = { apiV2.getShopV2(shopId) })
            .fromDataResponse()
    }

    override suspend fun getShopChain(
        page: Int?,
        count: Int?,
        language: String
    ): Result<PagedResponse<ShopChainResponse>> {
        return callApi(call = { apiV2.getShopChain(page, count, language) })
            .fromDataResponse()
    }

    override suspend fun getShopChainDetail(
        storeChainId: Int,
        language: String
    ): Result<ShopChainResponse> {
        return callApi(call = { apiV2.getShopChainDetail(storeChainId, language) })
            .fromDataResponse()
    }

    override suspend fun getRubrics(
        shopId: Int,
        language: String
    ): Result<CollectionResponse<RubricResponse>> {
        return callApi(call = { apiV2.getRubrics(shopId, language) })
            .fromDataResponse()
    }

    override suspend fun getOffers(
        shopId: Int,
        page: Int?,
        count: Int?,
        rubricId: String?
    ): Result<PagedResponse<OfferResponse>> {
        return callApi(call = { apiV1.getOffers(shopId, page, count, rubricId) })
            .sFlatMap { pagedResponseOpt ->
                pagedResponseOpt.sIfPresentOrDefault(
                    { pager ->
                        updatePreOrdersCounter(
                            shopId, pager.items, shopsDataSource, Dispatchers.IO
                        )
                        Result.Success(pager)
                    },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun findOffers(
        shopId: Int,
        query: String,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<OfferResponse>> {
        return callApi(call = { apiV1.findOffers(shopId, query, page, count) })
            .sFlatMap { pagedResponseOpt ->
                pagedResponseOpt.sIfPresentOrDefault(
                    { pager ->
                        updatePreOrdersCounter(
                            shopId, pager.items, shopsDataSource, Dispatchers.IO
                        )
                        Result.Success(pager)
                    },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getPromoOffers(
        shopId: Int,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<OfferResponse>> {
        return callApi(call = { apiV1.getPromoOffers(shopId, page, count) })
            .sFlatMap { pagedResponseOpt ->
                pagedResponseOpt.sIfPresentOrDefault(
                    { pager ->
                        updatePreOrdersCounter(
                            shopId, pager.items, shopsDataSource, Dispatchers.IO
                        )
                        Result.Success(pager)
                    },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getNoveltyOffers(
        shopId: Int,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<OfferResponse>> {
        return callApi(call = { apiV1.getNoveltyOffers(shopId, page, count) })
            .sFlatMap { pagedResponseOpt ->
                pagedResponseOpt.sIfPresentOrDefault(
                    { pager ->
                        updatePreOrdersCounter(
                            shopId, pager.items, shopsDataSource, Dispatchers.IO
                        )
                        Result.Success(pager)
                    },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getFavouriteOffers(
        shopId: Int,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<OfferResponse>> {
        return callApi(call = { apiV1.getFavouriteOffers(shopId, page, count) })
            .sFlatMap { pagedResponseOpt ->
                pagedResponseOpt.sIfPresentOrDefault(
                    { pager ->
                        updatePreOrdersCounter(
                            shopId, pager.items, shopsDataSource, Dispatchers.IO
                        )
                        Result.Success(pager)
                    },
                    { Result.Failure(ApiException()) })
            }
    }

    override suspend fun getPersistedShop(shopId: Int): Result<Optional<ShopEntity>> {
        return callQuery(call = { shopsDataSource.getShop(shopId) })
    }

    override suspend fun upsertPersistedShop(shop: ShopEntity): Result<Boolean> {
        return callQuery(call = { shopsDataSource.getShop(shop.id) })
            .sFlatMap { shopEntityOpt ->
                shopEntityOpt.sIfPresentOrDefault(
                    {
                        when (val updateShopQueryRes =
                            callQuery(call = { shopsDataSource.updateShop(shop) })) {
                            is Result.Success -> Result.Success(true)
                            is Result.Failure -> updateShopQueryRes
                        }
                    },
                    {
                        when (val insertShopQueryRes =
                            callQuery(call = { shopsDataSource.insertShop(shop) })) {
                            is Result.Success -> Result.Success(true)
                            is Result.Failure -> insertShopQueryRes
                        }
                    })
            }
    }

    override suspend fun updatePersistedShop(shop: ShopEntity): Result<Boolean> {
        return callQuery(call = { shopsDataSource.updateShop(shop) })
    }

    override suspend fun deletePersistedShop(shop: ShopEntity): Result<Boolean> {
        return callQuery(call = { shopsDataSource.deleteShop(shop) })
            .sOnSuccess {
                withContext(Dispatchers.Main.immediate) {
                    CoreBusEventsFactory.ordersPrice(shop.id)
                }
            }
    }

    override suspend fun deletePersistedShops(): Result<Boolean> {
        return callQuery(call = { shopsDataSource.deleteShops() })
            .sOnSuccess {
                withContext(Dispatchers.Main.immediate) {
                    CoreBusEventsFactory.ordersTotalPrice()
                }
            }
    }

    override suspend fun getPersistedOffer(
        shopId: Int,
        offerId: Int
    ): Result<Optional<OfferEntity>> {
        return callQuery(call = { shopsDataSource.getOrder(offerId, shopId) })
    }

    override suspend fun getPersistedOffers(): Result<List<ShopEntity>> {
        return callQuery(call = { shopsDataSource.getOrders() })
    }

    override suspend fun getPersistedOffers(shopId: Int): Result<List<OfferEntity>> {
        return callQuery(call = { shopsDataSource.getOrders(shopId) })
    }

    override suspend fun updatePersistedOffer(
        shopId: Int,
        offer: OfferResponse
    ): Result<Boolean> {
        return callQuery(call = { shopsDataSource.getOrder(offer.id, shopId) })
            .sFlatMap { orderEntityOpt ->
                orderEntityOpt.sIfPresentOrDefault(
                    {
                        val offerEntity = offer.toOfferEntity(it.shopId, it.preOrdersCount)
                        when (val updateOrderQueryRes =
                            callQuery(call = { shopsDataSource.updateOrder(offerEntity) })) {
                            is Result.Success -> Result.Success(true)
                            is Result.Failure -> updateOrderQueryRes
                        }
                    },
                    { Result.Failure(DbQueryException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)) })
            }
    }

    override suspend fun deletePreOrders(): Result<Boolean> {
        return callQuery(call = { shopsDataSource.deleteOrders() })
            .sOnSuccess {
                withContext(Dispatchers.Main.immediate) {
                    CoreBusEventsFactory.ordersTotalPrice()
                }
            }
    }

    override suspend fun incrementPreOrderCounter(shopId: Int, offerId: Int): Result<OfferEntity> {
        return callQuery(call = { shopsDataSource.getOrder(offerId, shopId) })
            .sFlatMap { orderEntityOpt ->
                orderEntityOpt.sIfPresentOrDefault(
                    { orderEntity ->
                        orderEntity.preOrdersCount = orderEntity.preOrdersCount.inc()
                        when (val updateOrderQueryRes =
                            callQuery(call = { shopsDataSource.updateOrder(orderEntity) })) {
                            is Result.Success -> {
                                if (updateOrderQueryRes.data) {
                                    Result.Success(orderEntity)
                                } else {
                                    Result.Failure(DbQueryException(DbErrorKeys.UPDATE_QUERY_ERROR))
                                }
                            }
                            is Result.Failure -> updateOrderQueryRes
                        }
                    },
                    { Result.Failure(DbQueryException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)) })
            }.sOnSuccess { orderEntity ->
                CoreBusEventsFactory.orderCounter(
                    shopId = orderEntity.shopId,
                    offerId = orderEntity.id,
                    counter = orderEntity.preOrdersCount
                )
                populatePersistedOffersPrice()
                populatePersistedOffersPrice(orderEntity.shopId)
            }
    }

    override suspend fun incrementPreOrderCounter(
        shopId: Int,
        offer: OfferResponse
    ): Result<OfferEntity> {
        return callQuery(call = { shopsDataSource.getShop(shopId) })
            .sFlatMap { shopEntityOpt ->
                shopEntityOpt.sIfPresentOrDefault(
                    { Result.Success(it) as Result<ShopEntity> }, // Keep this cast, otherwise types erasure will happen.
                    {
                        val shopEntity = ShopEntity(shopId)
                        when (val insertShopQueryRes =
                            callQuery(call = { shopsDataSource.insertShop(shopEntity) })) {
                            is Result.Success -> {
                                if (insertShopQueryRes.data) {
                                    Result.Success(shopEntity)
                                } else {
                                    Result.Failure(DbQueryException(DbErrorKeys.QUERY_ERROR))
                                }
                            }
                            is Result.Failure -> insertShopQueryRes
                        }
                    })
            }.sFlatMap { shopEntity ->
                when (val selectOfferQueryRes =
                    callQuery(call = { shopsDataSource.getOffer(requireNotNull(offer.id)) })) {
                    is Result.Success -> {
                        selectOfferQueryRes.data.sIfPresentOrDefault(
                            { offerEntity ->
                                when (val selectOrderQueryRes = callQuery(call = {
                                    shopsDataSource.getOrder(
                                        requireNotNull(offerEntity.id), shopId
                                    )
                                })) {
                                    is Result.Success -> {
                                        selectOrderQueryRes.data.sIfPresentOrDefault(
                                            { orderEntity ->
                                                val updatedOrderEntity = offer.toOfferEntity(
                                                    shopEntity.id,
                                                    orderEntity.preOrdersCount.inc()
                                                )
                                                when (val updateOrderQueryRes = callQuery(call = {
                                                    shopsDataSource.updateOrder(updatedOrderEntity)
                                                })) {
                                                    is Result.Success -> {
                                                        if (updateOrderQueryRes.data) {
                                                            Result.Success(updatedOrderEntity)
                                                        } else {
                                                            Result.Failure(
                                                                DbQueryException(
                                                                    DbErrorKeys.UPDATE_QUERY_ERROR
                                                                )
                                                            )
                                                        }
                                                    }
                                                    is Result.Failure -> updateOrderQueryRes
                                                }
                                            },
                                            {
                                                val newOrderEntity =
                                                    offer.toOfferEntity(shopEntity.id, 1)
                                                when (val insertOfferQueryRes = callQuery(call = {
                                                    shopsDataSource.insertOrder(newOrderEntity)
                                                })) {
                                                    is Result.Success -> {
                                                        if (insertOfferQueryRes.data) {
                                                            Result.Success(newOrderEntity)
                                                        } else {
                                                            Result.Failure(
                                                                DbQueryException(
                                                                    DbErrorKeys.INSERT_QUERY_ERROR
                                                                )
                                                            )
                                                        }
                                                    }
                                                    is Result.Failure -> insertOfferQueryRes
                                                }
                                            }
                                        )
                                    }
                                    is Result.Failure -> selectOrderQueryRes
                                }
                            },
                            {
                                val newOrderEntity = offer.toOfferEntity(shopEntity.id, 1)
                                when (val insertOfferQueryRes =
                                    callQuery(call = { shopsDataSource.insertOrder(newOrderEntity) })) {
                                    is Result.Success -> {
                                        if (insertOfferQueryRes.data) {
                                            Result.Success(newOrderEntity)
                                        } else {
                                            Result.Failure(DbQueryException(DbErrorKeys.INSERT_QUERY_ERROR))
                                        }
                                    }
                                    is Result.Failure -> insertOfferQueryRes
                                }
                            })
                    }
                    is Result.Failure -> selectOfferQueryRes
                }
            }.sOnSuccess { offerEntity ->
                CoreBusEventsFactory.orderCounter(
                    shopId = offerEntity.shopId,
                    offerId = offerEntity.id,
                    counter = offerEntity.preOrdersCount
                )
                populatePersistedOffersPrice()
                populatePersistedOffersPrice(offerEntity.shopId)
            }
    }

    override suspend fun decrementPreOrderCounter(shopId: Int, offerId: Int): Result<OfferEntity> {
        return callQuery(call = { shopsDataSource.getOrder(offerId, shopId) })
            .sFlatMap { orderEntityOpt ->
                orderEntityOpt.sIfPresentOrDefault(
                    { orderEntity ->
                        if (orderEntity.preOrdersCount > 0) {
                            orderEntity.preOrdersCount = orderEntity.preOrdersCount.dec()
                        }
                        if (orderEntity.preOrdersCount > 0) {
                            when (val updateOfferQueryRes =
                                callQuery(call = { shopsDataSource.updateOrder(orderEntity) })) {
                                is Result.Success -> Result.Success(orderEntity)
                                is Result.Failure -> updateOfferQueryRes
                            }

                        } else {
                            when (val deleteOfferQueryRes =
                                callQuery(call = { shopsDataSource.deleteOrder(orderEntity) })) {
                                is Result.Success -> Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE))
                                is Result.Failure -> deleteOfferQueryRes
                            }
                        }
                    },
                    { Result.Failure(DbQueryException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)) })
            }.sOnSuccess { orderEntity ->
                CoreBusEventsFactory.orderCounter(
                    shopId = orderEntity.shopId,
                    offerId = orderEntity.id,
                    counter = orderEntity.preOrdersCount
                )
                populatePersistedOffersPrice()
                populatePersistedOffersPrice(orderEntity.shopId)
            }.sOnFailure { error ->
                if (error.message == DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE) {
                    CoreBusEventsFactory.orderCounter(
                        shopId = shopId, offerId = offerId
                    )
                    populatePersistedOffersPrice()
                    populatePersistedOffersPrice(shopId)
                }
            }
    }

    override suspend fun decrementPreOrderCounter(
        shopId: Int,
        offer: OfferResponse
    ): Result<OfferEntity> {
        return callQuery(call = { shopsDataSource.getShop(shopId) })
            .sFlatMap { shopEntityOpt ->
                shopEntityOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)) })
            }.sFlatMap { shopEntity ->
                when (val selectOfferQueryRes = callQuery(call = {
                    shopsDataSource.getOrder(
                        requireNotNull(offer.id),
                        shopEntity.id
                    )
                })) {
                    is Result.Success -> {
                        selectOfferQueryRes.data.sIfPresentOrDefault(
                            {
                                val offerEntity = offer.toOfferEntity(it.shopId, it.preOrdersCount)
                                if (offerEntity.preOrdersCount > 0) {
                                    offerEntity.preOrdersCount = offerEntity.preOrdersCount.dec()
                                }
                                if (offerEntity.preOrdersCount > 0) {
                                    when (val updateOfferQueryRes =
                                        callQuery(call = { shopsDataSource.updateOrder(offerEntity) })) {
                                        is Result.Success -> Result.Success(offerEntity)
                                        is Result.Failure -> updateOfferQueryRes
                                    }

                                } else {
                                    when (val deleteOfferQueryRes =
                                        callQuery(call = { shopsDataSource.deleteOrder(offerEntity) })) {
                                        is Result.Success -> Result.Failure(
                                            DatabaseException(
                                                DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE
                                            )
                                        )
                                        is Result.Failure -> deleteOfferQueryRes
                                    }
                                }
                            },
                            { Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)) })
                    }
                    is Result.Failure -> selectOfferQueryRes
                }
            }.sOnSuccess { offerEntity ->
                CoreBusEventsFactory.orderCounter(
                    shopId = offerEntity.shopId,
                    offerId = offerEntity.id,
                    counter = offerEntity.preOrdersCount
                )
                populatePersistedOffersPrice()
                populatePersistedOffersPrice(offerEntity.shopId)
            }.sOnFailure { error ->
                if (error.message == DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE) {
                    CoreBusEventsFactory.orderCounter(
                        shopId = shopId, offerId = offer.id
                    )
                    populatePersistedOffersPrice()
                    populatePersistedOffersPrice(shopId)
                }
            }
    }

    override suspend fun populatePersistedOffersPrice(shopId: Int?) {
        if (shopId == null) {
            callQuery(call = { shopsDataSource.getOrders() })
                .onSuccess { shops ->
                    var totalCount = 0
                    var totalPrice = 0.0
                    shops.forEach { shop ->
                        val count = calculatePersistedOffersCount(shop.offers)
                        val price = calculatePersistedOffersPrice(shop.offers)
                        CoreBusEventsFactory.ordersPrice(shop.id, count, price)
                        totalCount += count
                        totalPrice += price
                    }
                    CoreBusEventsFactory.ordersTotalPrice(totalCount, totalPrice)
                }

        } else {
            callQuery(call = { shopsDataSource.getOrders(shopId) })
                .onSuccess { offers ->
                    CoreBusEventsFactory.ordersPrice(
                        shopId,
                        calculatePersistedOffersCount(offers),
                        calculatePersistedOffersPrice(offers)
                    )
                }
        }
    }
}