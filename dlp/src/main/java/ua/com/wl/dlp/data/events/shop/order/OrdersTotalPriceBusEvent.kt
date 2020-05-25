package ua.com.wl.dlp.data.events.shop.order

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class OrdersTotalPriceBusEvent(
    val count: Int,
    val price: Double
) : BusEvent()