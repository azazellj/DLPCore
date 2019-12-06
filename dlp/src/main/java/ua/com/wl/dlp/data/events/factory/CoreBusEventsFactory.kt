package ua.com.wl.dlp.data.events.factory

import ua.com.wl.archetype.core.android.bus.Bus
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.events.session.SessionBusEvent
import ua.com.wl.dlp.data.events.shop.offer.OfferBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrderCounterBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrderRateBusEvent
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

    fun orderRate(
        shopId: Int,
        orderId: Int,
        orderRate: Int = 0
    ) {
        Bus.send(OrderRateBusEvent(shopId, orderId, orderRate))
    }

    fun ordersPrice(
        shopId: Int,
        count: Int = 0,
        price: Double = 0.0
    ) {
        Bus.send(OrdersPriceBusEvent(shopId, count, price))
    }

    fun orderCounter(
        shopId: Int,
        tradeId: Int? = null,
        counter: Int = 0,
        resetAll: Boolean = false
    ) {
        val event = OrderCounterBusEvent(shopId, tradeId, counter, resetAll)
        Bus.send(event)
    }

    fun session(fallbackType: SessionBusEvent.FallbackType) {
        Bus.send(SessionBusEvent(fallbackType))
    }
}