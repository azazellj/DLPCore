package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo

@JsonClass(generateAdapter = true)
data class GeneralPreOrderRequest(
    @Json(name = "info")
    val info: BasePreOrderInfo?,

    @Json(name = "pre_orders")
    val preOrders: List<MultiPreOrderRequest>
)