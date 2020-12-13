package ua.com.wl.dlp.data.api.responses.orders.order.rate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.orders.order.rate.Order

@JsonClass(generateAdapter = true)
open class BaseOrderRateResponse(
    @Json(name = "value")
    var value: Int = 0,

    @Json(name = "comment")
    var comment: String? = null,

    @Json(name = "order")
    var order: Order? = null
)