package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreOrderReceipt(
    @Json(name = "count")
    val count: Int,

    @Json(name = "price")
    val price: String,

    @Json(name = "offer")
    val offer: PreOrderOffer
)