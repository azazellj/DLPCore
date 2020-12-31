package ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreOrderItem(
    @Json(name = "offer")
    val offer: Int,

    @Json(name = "count")
    val count: Int
)