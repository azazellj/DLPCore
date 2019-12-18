package ua.com.wl.dlp.data.events.shop.order

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class OrderCounterBusEvent(
    val shopId: Int,
    val tradeId: Int? = null,
    val counter: Int = 0,
    val resetAllCounters: Boolean = false
) : BusEvent()