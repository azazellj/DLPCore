package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class OrderItem(
    @Json(name = "price")
    val price: String,

    @Json(name = "quantity")
    val quantity: String,

    @Json(name = "total_price")
    val totalPrice: String,

    @Json(name = "product")
    val product: OrderProduct
) : Parcelable