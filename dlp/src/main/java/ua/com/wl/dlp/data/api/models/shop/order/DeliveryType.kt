package ua.com.wl.dlp.data.api.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class DeliveryType(val value: String) {
    @SerializedName("DELIVERY")
    DELIVERY("DELIVERY"),
    @SerializedName("TAKEAWAY")
    TAKEAEAY("TAKEAWAY")
}