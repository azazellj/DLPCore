package ua.com.wl.dlp.data.api.responses.models.consumer.coupons

import com.squareup.moshi.Json


enum class CouponStatus {
    @Json(name = "ACTIVE")
    ACTIVE,

    @Json(name = "INACTIVE")
    INACTIVE
}