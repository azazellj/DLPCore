package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.OffersApi
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.interactors.OffersInteractor

/**
 * @author Denis Makovskyi
 */

class OffersInteractorImpl(
    errorsMapper: ErrorsMapper,
    private val api: OffersApi): OffersInteractor, UseCase(errorsMapper) {

    override suspend fun addOfferToFavourites(offerId: Int): Result<Boolean> =
        callApi(call = { api.addOfferToFavourite(offerId) })
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
        callApi(call = { api.removeOfferFromFavourite(offerId) })
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
        callApi(call = { api.getOffer(offerId) })
            .flatMap { response ->
                response.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }
}