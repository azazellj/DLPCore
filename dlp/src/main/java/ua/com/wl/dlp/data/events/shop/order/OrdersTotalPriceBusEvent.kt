package ua.com.wl.dlp.data.events.shop.order

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class OrdersTotalPriceBusEvent(
    val count: Int = 0,
    val price: Double = 0.0
) : BusEvent()