package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class DeliveryType(val value: String) {
    @SerializedName("DELIVERY")
    DELIVERY("DELIVERY"),
    @SerializedName("TAKEAWAY")
    TAKEAWAY("TAKEAWAY"),
    @SerializedName("IN_PLACE")
    IN_PLACE("IN_PLACE")
}