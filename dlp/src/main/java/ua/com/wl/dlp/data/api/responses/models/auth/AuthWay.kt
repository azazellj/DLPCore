package ua.com.wl.dlp.data.api.responses.models.auth

import com.squareup.moshi.Json


enum class AuthWay {
    @Json(name = "STATIC_PASSWORD")
    STATIC_PASSWORD,

    @Json(name = "ALWAYS_SEND_SMS")
    ALWAYS_SEND_SMS,

    @Json(name = "BINDING_BY_LOYALTY_CARD")
    BINDING_BY_LOYALTY_CARD
}