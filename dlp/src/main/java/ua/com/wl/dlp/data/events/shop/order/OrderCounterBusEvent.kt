package ua.com.wl.dlp.data.events.shop.order

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class OrderCounterBusEvent(
    val tradeId: Int? = null,
    val counter: Int? = null,
    val resetAllCounters: Boolean = true
) : BusEvent()