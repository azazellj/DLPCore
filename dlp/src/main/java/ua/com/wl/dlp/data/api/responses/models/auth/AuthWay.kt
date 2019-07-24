package ua.com.wl.dlp.data.api.responses.models.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class AuthWay(val value: String) {
    @SerializedName("STATIC_PASSWORD")
    STATIC_PASSWORD("STATIC_PASSWORD"),
    @SerializedName("ALWAYS_SEND_SMS")
    ALWAYS_SEND_SMS("ALWAYS_SEND_SMS"),
    @SerializedName("BINDING_BY_LOYALTY_CARD")
    BINDING_BY_LOYALTY_CARD("BINDING_BY_LOYALTY_CARD")
}