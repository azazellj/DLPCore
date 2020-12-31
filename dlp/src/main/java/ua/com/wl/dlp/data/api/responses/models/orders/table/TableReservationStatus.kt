package ua.com.wl.dlp.data.api.responses.models.orders.table

import com.squareup.moshi.Json


enum class TableReservationStatus {
    @Json(name = "OPENED")
    OPENED,

    @Json(name = "CONFIRMED")
    CONFIRMED,

    @Json(name = "REJECTED")
    REJECTED,

    @Json(name = "REJECTED_BY_CONSUMER")
    REJECTED_BY_CONSUMER
}