package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductCategory(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
)