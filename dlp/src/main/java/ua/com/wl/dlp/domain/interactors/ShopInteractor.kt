package ua.com.wl.dlp.domain.interactors

import java.util.*

import ua.com.wl.archetype.utils.Optional

import ua.com.wl.dlp.data.api.requests.shop.order.PreOrderCreationRequest
import ua.com.wl.dlp.data.api.requests.shop.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.order.*
import ua.com.wl.dlp.data.api.responses.shop.rubric.RubricResponse
import ua.com.wl.dlp.data.api.responses.shop.table.TableReservationResponse
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface ShopInteractor: OffersInteractor {

    suspend fun getCityShops(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<CityShopsResponse>>

    suspend fun getShop(shopId: Int): Result<ShopResponse>

    suspend fun getRubrics(
        shopId: Int,
        language: String = Locale.getDefault().language
    ): Result<CollectionResponse<RubricResponse>>

    suspend fun getOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null,
        rubricId: String? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getPromoOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getNoveltyOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getFavouriteOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getOrders(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<OrderSimpleResponse>>

    suspend fun getOrder(orderId: Int): Result<OrderResponse>

    suspend fun rateOrder(
        orderId: Int,
        value: Int,
        comment: String = ""
    ): Result<BaseOrderRateResponse>

    suspend fun getOrderRate(orderId: Int): Result<OrderRateResponse>

    suspend fun getLastOrderRate(): Result<OrderRateResponse>

    suspend fun saveShopInDb(shop: ShopEntity): Result<Boolean>

    suspend fun deleteShopFromDb(shop: ShopEntity): Result<Boolean>

    suspend fun persistShop(shopId: Int): Result<Optional<ShopEntity>>

    suspend fun getPersistedShop(): Result<List<ShopEntity>>

    suspend fun updatePersistedPreOrder(offer: BaseOfferResponse): Result<Boolean>

    suspend fun incrementPersistedPreOrderCounter(
        shopId: Int,
        offer: BaseOfferResponse
    ): Result<OfferEntity>

    suspend fun decrementPersistedPreOrderCounter(
        shopId: Int,
        offer: BaseOfferResponse
    ): Result<OfferEntity>

    suspend fun populatePersistedPreOrdersPrice(shopId: Int)

    suspend fun getPersistedPreOrder(offerId: Int): Result<Optional<OfferEntity>>

    suspend fun getPersistedPreOrders(shopId: Int): Result<List<OfferEntity>>

    suspend fun createPreOrder(request: PreOrderCreationRequest): Result<PreOrderCreationResponse>

    suspend fun getPreOrders(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BasePreOrderResponse>>

    suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse>

    suspend fun createTableReservation(request: TableReservationRequest): Result<TableReservationResponse>

    suspend fun getTablesReservations(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<TableReservationResponse>>

    suspend fun getTableReservation(reservationId: Int): Result<TableReservationResponse>

    suspend fun cancelTableReservation(reservationId: Int): Result<Boolean>
}