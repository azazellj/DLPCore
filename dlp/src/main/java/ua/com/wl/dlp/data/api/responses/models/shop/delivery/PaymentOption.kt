package ua.com.wl.dlp.data.api.responses.models.shop.delivery

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentOption(
    @Json(name = "card")
    val card: Boolean,

    @Json(name = "cash")
    val cash: Boolean,

    @Json(name = "online")
    val online: Boolean
)