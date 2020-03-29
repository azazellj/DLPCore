package ua.com.wl.dlp.data.api.responses.models.consumer.coupons

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class CouponType(val value: String) {
    @SerializedName("VALUABLE")
    VALUABLE("VALUABLE"),

    @SerializedName("QUANTITATIVE")
    QUANTITATIVE("QUANTITATIVE")
}