package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderOffer(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "thumb_image")
    val thumbImage: String?
)