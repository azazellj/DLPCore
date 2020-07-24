package ua.com.wl.dlp.data.api.responses.consumer.coupons

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class CouponWalletResponse(
    @SerializedName("url")
    val url: String)
