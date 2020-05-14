package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class PaymentStatus(val value: String) {
    @SerializedName("inProcessing")
    IN_PROCESSING("inProcessing"),
    @SerializedName("WaitingAuthComplete")
    WAITING_AUTH_COMPLETE("WaitingAuthComplete"),
    @SerializedName("Pending")
    PENDING("Pending"),
    @SerializedName("Approved")
    APPROVED("Approved"),
    @SerializedName("Expired")
    EXPIRED("Expired"),
    @SerializedName("Refunded")
    REFUNDED("Refunded"),
    @SerializedName("Voided")
    VOIDED("Voided"),
    @SerializedName("Declined")
    DECLINED("Declined"),
    @SerializedName("RefundInProcessing")
    REFUND_IN_PROCESSING("RefundInProcessing")
}