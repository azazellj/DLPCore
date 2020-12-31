package ua.com.wl.dlp.data.api.requests.orders.order.rate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RateOrderRequest(
    @Json(name = "value")
    val value: Int,

    @Json(name = "comment")
    val comment: String
)