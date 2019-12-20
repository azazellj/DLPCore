package ua.com.wl.dlp.data.events.shop.order

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class OrderCounterBusEvent constructor(
    val shopId: Int,
    val offerId: Int,
    val counter: Int = 0
) : BusEvent()