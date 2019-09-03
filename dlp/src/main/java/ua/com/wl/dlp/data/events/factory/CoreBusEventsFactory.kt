package ua.com.wl.dlp.data.events.factory

import ua.com.wl.archetype.core.android.bus.Bus
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.events.session.SessionBusEvent
import ua.com.wl.dlp.data.events.shop.offer.OfferBusEvent
import ua.com.wl.dlp.utils.only

/**
 * @author Denis Makovskyi
 */

object CoreBusEventsFactory {

    fun profileChanges(changes: List<ProfileBusEvent.Change>) =
        ProfileBusEvent(changes).only { Bus.send(it) }

    fun offerFavouriteStatus(offerId: Int, tradeId: Int? = null, isFavourite: Boolean?) =
        OfferBusEvent(
            offerId, tradeId,
            OfferBusEvent.Field.IS_FAVOURITE,
            OfferBusEvent.FieldValue.BooleanValue(isFavourite)).only { Bus.send(it) }

    fun session(fallbackType: SessionBusEvent.FallbackType) =
        SessionBusEvent(fallbackType).only { Bus.send(it) }
}