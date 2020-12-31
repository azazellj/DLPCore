package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderItem(
    @Json(name = "price")
    val price: String,

    @Json(name = "quantity")
    val quantity: String,

    @Json(name = "total_price")
    val totalPrice: String,

    @Json(name = "product")
    val product: OrderProduct
)