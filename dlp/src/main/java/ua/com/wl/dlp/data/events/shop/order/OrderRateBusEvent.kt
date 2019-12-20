package ua.com.wl.dlp.data.events.shop.order

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class OrderRateBusEvent constructor(
    val shopId: Int,
    val orderId: Int,
    val orderRate: Int = 0
) : BusEvent()