package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class PreOrderStatus(val value: String) {
    @SerializedName("OPENED")
    OPENED("OPENED"),
    @SerializedName("PROCESSED")
    PROCESSED("PROCESSED"),
    @SerializedName("REJECTED")
    REJECTED("REJECTED"),
    @SerializedName("REJECTED_BY_CONSUMER")
    REJECTED_BY_CONSUMER("REJECTED_BY_CONSUMER")
}