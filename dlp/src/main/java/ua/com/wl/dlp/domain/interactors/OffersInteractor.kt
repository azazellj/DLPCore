package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface OffersInteractor {

    suspend fun addOfferToFavourites(offerId: Int): Result<Boolean>

    suspend fun removeOfferFromFavourites(offerId: Int): Result<Boolean>

    suspend fun getOffer(offerId: Int): Result<OfferResponse>
}