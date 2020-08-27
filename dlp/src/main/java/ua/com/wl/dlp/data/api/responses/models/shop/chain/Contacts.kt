package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.google.gson.annotations.SerializedName

class Contacts(
    @SerializedName("phone")
    var phone: Int = 0,

    @SerializedName("link")
    var webLinkUrl: String = "",

    @SerializedName("fb_link")
    var facebookLinkUrl: String? = null,

    @SerializedName("insta_link")
    var instagramLinkUrl: String? = null)