package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderInfo
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderPayment
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderReceipt

/**
 * @author Denis Makovskyi
 */

data class PreOrderResponse(
    @SerializedName("comment")
    val comment: String?,

    @SerializedName("info")
    val info: PreOrderInfo?,

    @SerializedName("payment")
    val payment: PreOrderPayment?,

    @SerializedName("receipt")
    val receipt: List<PreOrderReceipt>
) : BasePreOrderResponse()