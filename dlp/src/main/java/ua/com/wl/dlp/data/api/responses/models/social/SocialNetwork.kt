package ua.com.wl.dlp.data.api.responses.models.social

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SocialNetwork(
    @SerializedName("code")
    val code: SocialNetworkCode)