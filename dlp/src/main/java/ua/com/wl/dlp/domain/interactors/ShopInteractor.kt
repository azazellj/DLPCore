package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.ShopOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseShopOfferResponse
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

    suspend fun getShopPromoOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null

    ): Result<PagedResponse<BaseShopOfferResponse>>

    suspend fun getShopFavouriteOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null

    ): Result<PagedResponse<BaseShopOfferResponse>>

    suspend fun addShopOfferToFavourite(offerId: Int): Result<Boolean>

    suspend fun removeShopOfferFromFavourite(offerId: Int): Result<Boolean>

    suspend fun getShopOffer(offerId: Int): Result<ShopOfferResponse>
}