package ua.com.wl.dlp.data.api.responses.models.social

import com.squareup.moshi.Json

enum class SocialNetworkCode {
    @Json(name = "GP")
    GOOGLE,

    @Json(name = "FB")
    FACEBOOK
}