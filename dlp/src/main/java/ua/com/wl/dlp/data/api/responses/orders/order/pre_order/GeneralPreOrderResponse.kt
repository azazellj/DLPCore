package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GeneralPreOrderResponse(
    @Json(name = "pre_orders")
    val preOrders: List<PreOrderResponse>
)