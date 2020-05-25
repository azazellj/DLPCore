package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class PreOrderStatus(val value: String) {
    @SerializedName("OPENED")
    OPENED("OPENED"),
    @SerializedName("REJECTED")
    REJECTED("REJECTED"),
    @SerializedName("CONFIRMED")
    CONFIRMED("CONFIRMED"),
    @SerializedName("PROCESSED")
    PROCESSED("PROCESSED"),
    @SerializedName("IN_DELIVERY")
    IN_DELIVERY("IN_DELIVERY"),
    @SerializedName("CLOSED")
    CLOSED("CLOSED")
}