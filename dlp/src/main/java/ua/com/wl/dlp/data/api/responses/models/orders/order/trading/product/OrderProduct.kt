package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderProduct(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "price")
    val price: String,

    @Json(name = "photo")
    val photo: String?,

    @Json(name = "category")
    val category: ProductCategory
)