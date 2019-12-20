package ua.com.wl.dlp.data.api.responses.auth

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.auth.CardsStatus

/**
 * @author Denis Makovskyi
 */

data class CardsStatusResponse constructor(
    @SerializedName(value = "card_status", alternate = ["cards_status"])
    val cardsStatus: CardsStatus)