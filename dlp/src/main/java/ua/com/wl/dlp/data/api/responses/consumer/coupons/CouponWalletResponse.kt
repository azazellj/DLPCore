package ua.com.wl.dlp.data.api.responses.consumer.coupons

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CouponWalletResponse(
    @Json(name = "url")
    val url: String
)
