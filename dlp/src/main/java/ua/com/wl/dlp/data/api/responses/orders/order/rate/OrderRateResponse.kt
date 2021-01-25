package ua.com.wl.dlp.data.api.responses.orders.order.rate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ua.com.wl.dlp.data.api.responses.models.orders.order.ConsumerOrder

@JsonClass(generateAdapter = true)
@Parcelize
data class OrderRateResponse(
    @Json(name = "rs_order")
    val consumerOrder: ConsumerOrder?
) : BaseOrderRateResponse()