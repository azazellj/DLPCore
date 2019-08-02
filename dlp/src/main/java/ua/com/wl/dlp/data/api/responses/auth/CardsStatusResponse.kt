package ua.com.wl.dlp.data.api.responses.auth

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.auth.CardsStatus

/**
 * @author Denis Makovskyi
 */

data class CardsStatusResponse(@SerializedName("cards_status") val cardsStatus: CardsStatus)