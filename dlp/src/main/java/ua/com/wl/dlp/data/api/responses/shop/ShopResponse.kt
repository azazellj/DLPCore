package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class ShopResponse(
    @SerializedName("phone")
    val phone: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("in_favorite")
    val isFavourite: Boolean,

    @SerializedName("link")
    val link: String,

    @SerializedName("fb_link")
    val facebookLink: String,

    @SerializedName("insta_link")
    val instagramLink: String
) : BaseShopResponse()