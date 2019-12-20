package ua.com.wl.dlp.data.events.factory

import ua.com.wl.archetype.core.android.bus.Bus
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.events.session.SessionBusEvent
import ua.com.wl.dlp.data.events.shop.offer.OfferBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrderCounterBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrderRateBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrdersPriceBusEvent
import ua.com.wl.dlp.data.events.shop.order.OrdersTotalPriceBusEvent

/**
 * @author Denis Makovskyi
 */

object CoreBusEventsFactory {

    internal fun profileChanges(
        changes: List<ProfileBusEvent.Change>) = Bus.send(ProfileBusEvent(changes))

    internal fun offerFavouriteStatus(
        offerId: Int,
        tradeId: Int? = null,
        isFavourite: Boolean?
    ) = Bus.send(
        OfferBusEvent(
            offerId, tradeId,
            OfferBusEvent.Field.IS_FAVOURITE,
            OfferBusEvent.FieldValue.BooleanValue(isFavourite)))

    internal fun orderRate(
        shopId: Int,
        orderId: Int,
        orderRate: Int = 0
    ) = Bus.send(OrderRateBusEvent(shopId, orderId, orderRate))

    internal fun orderCounter(
        shopId: Int,
        offerId: Int,
        counter: Int = 0
    ) = Bus.send(OrderCounterBusEvent(shopId, offerId, counter))

    internal fun ordersPrice(
        shopId: Int,
        count: Int = 0,
        price: Double = 0.0
    ) = Bus.send(OrdersPriceBusEvent(shopId, count, price))

    internal fun ordersTotalPrice(
        count: Int = 0,
        price: Double = 0.0
    ) = Bus.send(OrdersTotalPriceBusEvent(count, price))

    internal fun sessionExpired(
        fallbackType: SessionBusEvent.FallbackType) = Bus.send(SessionBusEvent(fallbackType))
}