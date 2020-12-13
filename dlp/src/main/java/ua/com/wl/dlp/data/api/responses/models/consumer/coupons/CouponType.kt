package ua.com.wl.dlp.data.api.responses.models.consumer.coupons

import com.squareup.moshi.Json

enum class CouponType {
    @Json(name = "VALUABLE")
    VALUABLE,

    @Json(name = "QUANTITATIVE")
    QUANTITATIVE
}