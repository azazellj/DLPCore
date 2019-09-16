package ua.com.wl.dlp.data.events.factory

import ua.com.wl.archetype.core.android.bus.Bus
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.events.session.SessionBusEvent
import ua.com.wl.dlp.data.events.shop.offer.OfferBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrderCounterBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrdersPriceBusEvent

/**
 * @author Denis Makovskyi
 */

object CoreBusEventsFactory {

    fun profileChanges(changes: List<ProfileBusEvent.Change>) {
        Bus.send(ProfileBusEvent(changes))
    }

    fun offerFavouriteStatus(
        offerId: Int,
        tradeId: Int? = null,
        isFavourite: Boolean?
    ) {
        val event = OfferBusEvent(
            offerId, tradeId,
            OfferBusEvent.Field.IS_FAVOURITE,
            OfferBusEvent.FieldValue.BooleanValue(isFavourite))
        Bus.send(event)
    }

    fun ordersPrice(shopId: Int, price: Double) {
        Bus.send(OrdersPriceBusEvent(shopId, price))
    }

    fun orderCounter(
        tradeId: Int? = null,
        counter: Int? = null,
        resetAll: Boolean = false
    ) {
        val event = OrderCounterBusEvent(tradeId, counter, resetAll)
        Bus.send(event)
    }

    fun session(fallbackType: SessionBusEvent.FallbackType) {
        Bus.send(SessionBusEvent(fallbackType))
    }
}