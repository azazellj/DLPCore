package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.requests.shop.order.PreOrderCreationRequest
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.order.BasePreOrderResponse
import ua.com.wl.dlp.data.api.responses.shop.order.PreOrderCreationResponse
import ua.com.wl.dlp.data.api.responses.shop.order.PreOrderResponse
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface ShopInteractor {

    suspend fun getCityShops(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<CityShopsResponse>>

    suspend fun getShop(shopId: Int): Result<ShopResponse>

    suspend fun getPromoOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun getFavouriteOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    suspend fun addOfferToFavourites(offerId: Int): Result<Boolean>

    suspend fun removeOfferFromFavourites(offerId: Int): Result<Boolean>

    suspend fun getOffer(offerId: Int): Result<OfferResponse>

    suspend fun upsertShopInDb(shop: ShopEntity): Result<Boolean>

    suspend fun deleteShopFromDb(shop: ShopEntity): Result<Boolean>

    suspend fun incrementPreOrderCounter(shopId: Int, offer: BaseOfferResponse): Result<OfferEntity>

    suspend fun decrementPreOrderCounter(shopId: Int, offer: BaseOfferResponse): Result<OfferEntity>

    suspend fun createPreOrder(request: PreOrderCreationRequest): Result<PreOrderCreationResponse>

    suspend fun getPreOrders(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BasePreOrderResponse>>

    suspend fun getPreOrder(preOrderId: Int): Result<PreOrderResponse>
}