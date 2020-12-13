package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderInfo
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderPayment
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderReceipt

@JsonClass(generateAdapter = true)
data class PreOrderResponse(
    @Json(name = "comment")
    val comment: String?,

    @Json(name = "info")
    val info: PreOrderInfo?,

    @Json(name = "payment")
    val payment: PreOrderPayment?,

    @Json(name = "receipt")
    val receipt: List<PreOrderReceipt>
) : BasePreOrderResponse()