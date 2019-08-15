package ua.com.wl.dlp.domain.interactors.impl

import ua.com.wl.dlp.data.api.ShopApiV1
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopNewsItemResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.interactors.ShopInteractor

/**
 * @author Denis Makovskyi
 */

class ShopInteractorImpl(private val apiV1: ShopApiV1, errorsMapper: ErrorsMapper): ShopInteractor, UseCase(errorsMapper) {

    override suspend fun getCityShops(page: Int?, count: Int?): Result<PagedResponse<CityShopsResponse>> =
        callApi(call = { apiV1.getCityShops(page, count) })

    override suspend fun getShopNewsFeed(shopId: Int, page: Int?, count: Int?): Result<PagedResponse<ShopNewsItemResponse>> =
        callApi(call = { apiV1.getShopNewsFeed(shopId, page, count) })

    override suspend fun getShopPromoOffers(shopId: Int, page: Int?, count: Int?): Result<PagedResponse<ShopOfferResponse>> =
        callApi(call = { apiV1.getShopPromoOffers(shopId, page, count) })
}