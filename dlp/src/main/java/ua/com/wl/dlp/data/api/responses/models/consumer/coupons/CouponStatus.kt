package ua.com.wl.dlp.data.api.responses.models.consumer.coupons

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class CouponStatus(val value: String) {
    @SerializedName("ACTIVE")
    ACTIVE("ACTIVE"),

    @SerializedName("INACTIVE")
    INACTIVE("INACTIVE")
}