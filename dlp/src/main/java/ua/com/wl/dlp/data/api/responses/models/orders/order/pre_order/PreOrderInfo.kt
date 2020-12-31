package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo

@JsonClass(generateAdapter = true)
data class PreOrderInfo(
    @Json(name = "payment_status")
    val paymentStatus: PaymentStatus
) : BasePreOrderInfo()