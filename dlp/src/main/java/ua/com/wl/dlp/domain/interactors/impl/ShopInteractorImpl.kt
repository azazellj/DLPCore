package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.api.ShopApiV1
import ua.com.wl.dlp.data.api.ShopApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoType
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
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
import ua.com.wl.dlp.utils.only
import ua.com.wl.dlp.utils.toOfferEntity

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

    override suspend fun getRubrics(
        shopId: Int,
        language: String): Result<CollectionResponse<RubricResponse>> =
        callApi(call = { apiV2.getRubrics(shopId, language) })
            .flatMap { dataResponse ->
                dataResponse.ifPresentOrDefault(
                    { Result.Success(it.payload) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getOffers(
        shopId: Int,
        page: Int?,
        count: Int?,
        rubricId: String?
    ): Result<PagedResponse<BaseOfferResponse>> =
        callApi(call = { apiV1.getOffers(shopId, page, count, rubricId) })
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
        callApi(call = { apiV1.getPromoOffers(shopId, page, count) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getNoveltyOffers(
        shopId: Int,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BaseOfferResponse>> =
        callApi(call = { apiV1.getNoveltyOffers(shopId, page, count) })
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
        callApi(call = { apiV1.getFavouriteOffers(shopId, page, count) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun persistShop(shop: ShopEntity): Result<Boolean> =
        callQuery(call = { shopsDataSource.upsertShop(shop) })
            .sOnSuccess { isSuccess ->
                if (isSuccess) {
                    val selectOffersQueryRes = callQuery(call = { shopsDataSource.getShopOffers(shop.id) })
                    if (selectOffersQueryRes is Result.Success) {
                        populatePersistedPreOrdersPrice(shop.id)
                    }
                }
            }

    override suspend fun updatePersistedShop(shop: ShopEntity): Result<Boolean> =
        callQuery(call = { shopsDataSource.updateShop(shop) })
            .sOnSuccess { isSuccess ->
                if (isSuccess) {
                    val selectOffersQueryRes = callQuery(call = { shopsDataSource.getShopOffers(shop.id) })
                    if (selectOffersQueryRes is Result.Success) {
                        populatePersistedPreOrdersPrice(shop.id)
                    }
                }
            }

    override suspend fun getPersistedShop(shopId: Int): Result<Optional<ShopEntity>> =
        callQuery(call = { shopsDataSource.getShop(shopId) })

    override suspend fun getPersistedShops(): Result<List<ShopEntity>> =
        callQuery(call = { shopsDataSource.getShops() })

    override suspend fun deletePersistedShop(shop: ShopEntity): Result<Boolean> =
        callQuery(call = { shopsDataSource.deleteShop(shop) })
            .sOnEach {
                withContext(Dispatchers.Main.immediate) {
                    CoreBusEventsFactory.ordersPrice(shop.id)
                    CoreBusEventsFactory.orderCounter(shopId = shop.id, resetAll = true)
                }
            }

    override suspend fun updatePersistedOffer(offer: BaseOfferResponse): Result<Boolean> =
        callQuery(call = { shopsDataSource.getOffer(offer.id) })
            .sFlatMap { offerEntityOpt ->
                offerEntityOpt.sIfPresentOrDefault(
                    {
                        val offerEntity = offer.toOfferEntity(it.shopId, it.preOrderCount)
                        when(val upsertOfferQueryRes = callQuery(call = { shopsDataSource.upsertOffer(offerEntity) })) {
                            is Result.Success -> Result.Success(true)
                            is Result.Failure -> upsertOfferQueryRes
                        }
                    },
                    {
                        Result.Failure(DbQueryException(DbErrorKeys.ENTITY_IS_NOT_EXISTS))
                    })
            }

    override suspend fun incrementPersistedPreOrderCounter(offerId: Int): Result<OfferEntity> =
        callQuery(call = { shopsDataSource.getOffer(offerId) })
            .sFlatMap { offerEntityOpt ->
                offerEntityOpt.sIfPresentOrDefault(
                    {
                        val offerEntity = it.copy(preOrderCount = it.preOrderCount.inc())
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
                    },
                    {
                        Result.Failure(DbQueryException(DbErrorKeys.ENTITY_IS_NOT_EXISTS))
                    })
            }.sOnSuccess { offerEntity ->
                populatePersistedPreOrdersPrice(offerEntity.shopId)
                CoreBusEventsFactory.orderCounter(
                    shopId = offerEntity.shopId,
                    tradeId = offerEntity.tradeItem,
                    counter = offerEntity.preOrderCount)
            }

    override suspend fun incrementPersistedPreOrderCounter(shopId: Int, offer: BaseOfferResponse): Result<OfferEntity> =
        callQuery(call = { shopsDataSource.getShop(shopId) })
            .sFlatMap { shopEntityOpt ->
                shopEntityOpt.sIfPresentOrDefault(
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
            }.sFlatMap { shopEntity ->
                when(val selectOfferQueryRes = callQuery(call = { shopsDataSource.getOffer(requireNotNull(offer.id)) })) {
                    is Result.Success -> {
                        val offerEntity = selectOfferQueryRes.data.ifPresentOrDefault(
                            { offer.toOfferEntity(shopEntity.id, it.preOrderCount.inc()) },
                            { offer.toOfferEntity(shopEntity.id, 1) })
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
                populatePersistedPreOrdersPrice(offerEntity.shopId)
                CoreBusEventsFactory.orderCounter(
                    shopId = offerEntity.shopId,
                    tradeId = offerEntity.tradeItem,
                    counter = offerEntity.preOrderCount)
            }

    override suspend fun decrementPersistedPreOrderCounter(shopId: Int, offerId: Int, tradeItem: Int): Result<OfferEntity> =
        callQuery(call = { shopsDataSource.getOffer(offerId) })
            .sFlatMap { offerEntityOpt ->
                offerEntityOpt.sIfPresentOrDefault(
                    {
                        var offerEntity = it
                        if (offerEntity.preOrderCount > 0) {
                            offerEntity = offerEntity.copy(preOrderCount = offerEntity.preOrderCount.dec())
                        }
                        if (offerEntity.preOrderCount > 0) {
                            when(val upsertOfferQueryRes = callQuery(call = { shopsDataSource.upsertOffer(offerEntity) })) {
                                is Result.Success -> Result.Success(offerEntity)
                                is Result.Failure -> upsertOfferQueryRes
                            }

                        } else {
                            when(val deleteOfferQueryRes = callQuery(call = { shopsDataSource.deleteOffer(offerEntity) })) {
                                is Result.Success -> Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE))
                                is Result.Failure -> deleteOfferQueryRes
                            }
                        }
                    },
                    {
                        Result.Failure(DbQueryException(DbErrorKeys.ENTITY_IS_NOT_EXISTS))
                    })
            }.sOnSuccess { offerEntity ->
                populatePersistedPreOrdersPrice(offerEntity.shopId)
                CoreBusEventsFactory.orderCounter(
                    shopId = offerEntity.shopId,
                    tradeId = offerEntity.tradeItem,
                    counter = offerEntity.preOrderCount)
            }.sOnFailure { error ->
                if (error.message == DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE) {
                    populatePersistedPreOrdersPrice(shopId)
                    CoreBusEventsFactory.orderCounter(shopId = shopId, tradeId = tradeItem)
                }
            }

    override suspend fun decrementPersistedPreOrderCounter(shopId: Int, offer: BaseOfferResponse): Result<OfferEntity> =
        callQuery(call = { shopsDataSource.getShop(shopId) })
            .sFlatMap { shopEntityOpt ->
                shopEntityOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(DatabaseException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)) })
            }.sFlatMap { shop ->
                when(val selectOfferQueryRes = callQuery(call = { shopsDataSource.getOffer(requireNotNull(offer.id)) })) {
                    is Result.Success -> {
                        selectOfferQueryRes.data.sIfPresentOrDefault(
                            {
                                var offerEntity = offer.toOfferEntity(shop.id, it.preOrderCount)
                                if (offerEntity.preOrderCount > 0) {
                                    offerEntity = offerEntity.copy(preOrderCount = offerEntity.preOrderCount.dec())
                                }
                                if (offerEntity.preOrderCount > 0) {
                                    when(val upsertOfferQueryRes = callQuery(call = { shopsDataSource.upsertOffer(offerEntity) })) {
                                        is Result.Success -> Result.Success(offerEntity)
                                        is Result.Failure -> upsertOfferQueryRes
                                    }

                                } else {
                                    when(val deleteOfferQueryRes = callQuery(call = { shopsDataSource.deleteOffer(offerEntity) })) {
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
                populatePersistedPreOrdersPrice(offerEntity.shopId)
                CoreBusEventsFactory.orderCounter(
                    shopId = offerEntity.shopId,
                    tradeId = offerEntity.tradeItem,
                    counter = offerEntity.preOrderCount)
            }.sOnFailure { error ->
                if (error.message == DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE) {
                    populatePersistedPreOrdersPrice(shopId)
                    CoreBusEventsFactory.orderCounter(shopId = shopId, tradeId = offer.tradeItem)
                }
            }

    override suspend fun populatePersistedPreOrdersPrice(shopId: Int) {
        val selectOffersQueryRes = callQuery(call = { shopsDataSource.getShopOffers(shopId) })
        if (selectOffersQueryRes is Result.Success) {
            selectOffersQueryRes.data.only { offers ->
                val price = offers.sumByDouble { offer ->
                    if (offer.isPromoOffer) {
                        when(offer.promoSettings?.promoType) {
                            PromoType.SALE -> {
                                offer.promoSettings
                                    ?.promoParams
                                    ?.salePrice
                                    ?.toDouble()
                                    ?.times(offer.preOrderCount)
                                    ?: 0.0
                            }
                            PromoType.EVENT -> {
                                offer.promoSettings
                                    ?.promoParams
                                    ?.eventPrice
                                    ?.toDouble()
                                    ?.times(offer.preOrderCount)
                                    ?: 0.0
                            }
                            PromoType.DISCOUNT -> {
                                offer.promoSettings
                                    ?.promoParams
                                    ?.discountPrice
                                    ?.toDouble()
                                    ?.times(offer.preOrderCount)
                                    ?: 0.0
                            }
                            else -> offer.priceInCurrency?.toDouble()?.times(offer.preOrderCount) ?: 0.0
                        }

                    } else {
                        offer.priceInCurrency?.toDouble()?.times(offer.preOrderCount) ?: 0.0
                    }
                }
                CoreBusEventsFactory.ordersPrice(shopId, offers.size, price)
            }

        } else {
            CoreBusEventsFactory.ordersPrice(shopId)
        }
    }

    override suspend fun getPersistedOffer(offerId: Int): Result<Optional<OfferEntity>> =
        callQuery(call = { shopsDataSource.getOffer(offerId) })

    override suspend fun getPersistedOffers(shopId: Int): Result<List<OfferEntity>> =
        callQuery(call = { shopsDataSource.getShopOffers(shopId) })
}