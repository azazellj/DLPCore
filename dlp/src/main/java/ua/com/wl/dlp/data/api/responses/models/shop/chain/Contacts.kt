package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contacts(
    @Json(name = "phone")
    var phone: String = "",

    @Json(name = "link")
    var webLinkUrl: String = "",

    @Json(name = "fb_link")
    var facebookLinkUrl: String? = null,

    @Json(name = "insta_link")
    var instagramLinkUrl: String? = null
)