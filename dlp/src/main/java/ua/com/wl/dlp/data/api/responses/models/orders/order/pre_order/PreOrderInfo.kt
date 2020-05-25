package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo

/**
 * @author Denis Makovskyi
 */

data class PreOrderInfo(
    @SerializedName("payment_status")
    val paymentStatus: PaymentStatus
): BasePreOrderInfo()