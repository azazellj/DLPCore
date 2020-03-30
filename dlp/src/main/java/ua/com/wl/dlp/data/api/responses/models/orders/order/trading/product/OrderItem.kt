package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderItem(
    @SerializedName("price")
    val price: String,

    @SerializedName("quantity")
    val quantity: String,

    @SerializedName("total_price")
    val totalPrice: String,

    @SerializedName("product")
    val product: OrderProduct)