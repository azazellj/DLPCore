package ua.com.wl.dlp.domain.interactors

import java.util.*

import ua.com.wl.archetype.utils.Optional

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.rubric.RubricResponse
import ua.com.wl.dlp.data.api.responses.shop.chain.ShopChainResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface ShopInteractor : OffersInteractor {

    suspend fun getCityShops(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<CityShopsResponse>>

    suspend fun getShopsWithChain(
        page: Int?,
        count: Int?,
        language: String
    ): Result<PagedResponse<CityShopsResponse>>

    suspend fun getShop(shopId: Int): Result<ShopResponse>

    suspend fun getShopV2(shopId: Int): Result<ShopResponse>

    suspend fun getShopChain(
        page: Int?,
        count: Int?,
        language: String = Locale.getDefault().language
    ): Result<PagedResponse<ShopChainResponse>>

    suspend fun getShopChainDetail(
        storeChainId: Int,
        language: String = Locale.getDefault().language
    ): Result<ShopChainResponse>

    suspend fun getRubrics(
        shopId: Int,
        language: String = Locale.getDefault().language
    ): Result<CollectionResponse<RubricResponse>>

    suspend fun getOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null,
        rubricId: String? = null
    ): Result<PagedResponse<OfferResponse>>

    suspend fun findOffers(
        shopId: Int,
        query: String,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<OfferResponse>>

    @Deprecated(message = "This method will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
    suspend fun getPromoOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<OfferResponse>>

    suspend fun getNoveltyOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<OfferResponse>>

    suspend fun getFavouriteOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<OfferResponse>>

    suspend fun getPersistedShop(shopId: Int): Result<Optional<ShopEntity>>

    suspend fun upsertPersistedShop(shop: ShopEntity): Result<Boolean>

    suspend fun updatePersistedShop(shop: ShopEntity): Result<Boolean>

    suspend fun deletePersistedShop(shop: ShopEntity): Result<Boolean>

    suspend fun deletePersistedShops(): Result<Boolean>

    suspend fun getPersistedOffer(shopId: Int, offerId: Int): Result<Optional<OfferEntity>>

    suspend fun getPersistedOffers(): Result<List<ShopEntity>>

    suspend fun getPersistedOffers(shopId: Int): Result<List<OfferEntity>>

    suspend fun updatePersistedOffer(shopId: Int, offer: OfferResponse): Result<Boolean>

    suspend fun deletePreOrders(): Result<Boolean>

    suspend fun incrementPreOrderCounter(shopId: Int, offerId: Int): Result<OfferEntity>

    suspend fun incrementPreOrderCounter(shopId: Int, offer: OfferResponse): Result<OfferEntity>

    suspend fun decrementPreOrderCounter(shopId: Int, offerId: Int): Result<OfferEntity>

    suspend fun decrementPreOrderCounter(shopId: Int, offer: OfferResponse): Result<OfferEntity>

    suspend fun populatePersistedOffersPrice(shopId: Int? = null)
}