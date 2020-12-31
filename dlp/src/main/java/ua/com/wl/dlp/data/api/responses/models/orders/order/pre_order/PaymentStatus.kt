package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.squareup.moshi.Json

enum class PaymentStatus {
    @Json(name = "inProcessing")
    IN_PROCESSING,

    @Json(name = "WaitingAuthComplete")
    WAITING_AUTH_COMPLETE,

    @Json(name = "Pending")
    PENDING,

    @Json(name = "Approved")
    APPROVED,

    @Json(name = "Expired")
    EXPIRED,

    @Json(name = "Refunded")
    REFUNDED,

    @Json(name = "Voided")
    VOIDED,

    @Json(name = "Declined")
    DECLINED,

    @Json(name = "RefundInProcessing")
    REFUND_IN_PROCESSING
}