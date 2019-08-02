package ua.com.wl.dlp.data.api.responses.models.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class CardsStatus(val value: String) {
    @SerializedName("NO_CARDS")
    NO_CARDS("NO_CARDS"),
    @SerializedName("ONE_CARD")
    ONE_CARD("ONE_CARD"),
    @SerializedName("MULTIPLE_CARDS")
    MULTIPLE_CARDS("MULTIPLE_CARDS")
}