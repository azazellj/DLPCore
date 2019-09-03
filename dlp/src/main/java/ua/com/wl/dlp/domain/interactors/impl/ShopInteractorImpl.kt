package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.ShopApiV1
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.news.ShopNewsItemResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.ShopOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseShopOfferResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.utils.only

/**
 * @author Denis Makovskyi
 */

class ShopInteractorImpl(errorsMapper: ErrorsMapper, private val apiV1: ShopApiV1) : ShopInteractor, UseCase(errorsMapper) {

    override suspend fun getCityShops(page: Int?, count: Int?): Result<PagedResponse<CityShopsResponse>> =
        callApi(call = { apiV1.getCityShops(page, count) })

    override suspend fun getShop(shopId: Int): Result<ShopResponse> =
        callApi(call = { apiV1.getShop(shopId) })

    override suspend fun getShopNewsFeed(shopId: Int, page: Int?, count: Int?): Result<PagedResponse<ShopNewsItemResponse>> =
        callApi(call = { apiV1.getShopNewsFeed(shopId, page, count) })

    override suspend fun getShopPromoOffers(shopId: Int, page: Int?, count: Int?): Result<PagedResponse<BaseShopOfferResponse>> =
        callApi(call = { apiV1.getShopPromoOffers(shopId, page, count) })

    override suspend fun getShopFavouriteOffers(shopId: Int, page: Int?, count: Int?): Result<PagedResponse<BaseShopOfferResponse>> =
        callApi(call = { apiV1.getShopFavouriteOffers(shopId, page, count) })

    override suspend fun addShopOfferToFavourite(offerId: Int): Result<Boolean> =
        callApi(call = { apiV1.addShopOfferToFavourite(offerId) })
            .fMap { response ->
                response?.isSuccessfully()
            }.sOnSuccess { isSuccess ->
                isSuccess?.only {
                    if (it) {
                        withContext(Dispatchers.Main.immediate) {
                            CoreBusEventsFactory.offerFavouriteStatus(offerId = offerId, isFavourite = true)
                        }
                    }
                }
            }

    override suspend fun removeShopOfferFromFavourite(offerId: Int): Result<Boolean> =
        callApi(call = { apiV1.removeShopOfferFromFavourite(offerId) })
            .fMap { response ->
                response?.isSuccessfully()
            }.sOnSuccess { isSuccess ->
                isSuccess?.only {
                    if (it) {
                        withContext(Dispatchers.Main.immediate) {
                            CoreBusEventsFactory.offerFavouriteStatus(offerId = offerId, isFavourite = false)
                        }
                    }
                }
            }

    override suspend fun getShopOffer(offerId: Int): Result<ShopOfferResponse> =
        callApi(call = { apiV1.getShopOffer(offerId) })
}