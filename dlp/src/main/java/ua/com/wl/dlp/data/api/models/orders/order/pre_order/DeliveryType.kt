package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.squareup.moshi.Json

/**
 * @author Denis Makovskyi
 */

enum class DeliveryType {
    @Json(name = "DELIVERY")
    DELIVERY,

    @Json(name = "TAKEAWAY")
    TAKEAWAY,

    @Json(name = "IN_PLACE")
    IN_PLACE
}