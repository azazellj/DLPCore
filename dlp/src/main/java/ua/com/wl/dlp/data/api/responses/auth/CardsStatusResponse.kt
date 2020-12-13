package ua.com.wl.dlp.data.api.responses.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.auth.CardsStatus

@JsonClass(generateAdapter = true)
data class CardsStatusResponse(
    @Json(name = "card_status")
    val statusOfCard: CardsStatus?,
    @Json(name = "cards_status")
    val statusOfCards: CardsStatus?
) {
    val cardsStatus: CardsStatus
        get() {
            return statusOfCard ?: statusOfCards ?: CardsStatus.NO_CARDS
        }
}