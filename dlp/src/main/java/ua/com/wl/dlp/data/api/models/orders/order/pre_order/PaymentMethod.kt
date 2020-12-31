package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.squareup.moshi.Json

/**
 * @author Denis Makovskyi
 */

enum class PaymentMethod {
    @Json(name = "CASH")
    CASH,

    @Json(name = "BY_CARD")
    CARD,

    @Json(name = "ONLINE")
    ONLINE
}