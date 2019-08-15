package ua.com.wl.dlp.data.api.responses.models.shop.offer.social

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class SocialNetworkCode(val value: String) {
    @SerializedName("GP")
    GOOGLE("GP"),
    @SerializedName("FB")
    FACEBOOK("FB")
}