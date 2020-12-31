package ua.com.wl.dlp.data.api.responses.models.shop.delivery

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentOptions(
    @Json(name = "DELIVERY")
    val delivery: PaymentOption?,

    @Json(name = "TAKEAWAY")
    val takeaway: PaymentOption?
)