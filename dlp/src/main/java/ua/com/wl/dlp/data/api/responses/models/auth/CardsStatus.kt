package ua.com.wl.dlp.data.api.responses.models.auth

import com.squareup.moshi.Json

enum class CardsStatus {
    @Json(name = "NO_CARDS")
    NO_CARDS,

    @Json(name = "ONE_CARD")
    ONE_CARD,

    @Json(name = "MULTIPLE_CARDS")
    MULTIPLE_CARDS
}