package ua.com.wl.dlp.data.api.responses.models.social

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SocialNetwork(
    @Json(name = "code")
    val code: SocialNetworkCode
)