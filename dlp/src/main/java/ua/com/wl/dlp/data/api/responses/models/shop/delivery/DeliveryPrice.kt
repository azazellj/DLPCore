package ua.com.wl.dlp.data.api.responses.models.shop.delivery

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryPrice(
    @Json(name = "price")
    val price: Long,

    @Json(name = "total_price_from")
    val totalPriceFrom: String
)