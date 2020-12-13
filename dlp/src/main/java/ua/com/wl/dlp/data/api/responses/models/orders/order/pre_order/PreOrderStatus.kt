package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.squareup.moshi.Json

enum class PreOrderStatus {
    @Json(name = "OPENED")
    OPENED,

    @Json(name = "REJECTED")
    REJECTED,

    @Json(name = "CONFIRMED")
    CONFIRMED,

    @Json(name = "PROCESSED")
    PROCESSED,

    @Json(name = "IN_DELIVERY")
    IN_DELIVERY,

    @Json(name = "CLOSED")
    CLOSED
}