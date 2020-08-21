package ua.com.wl.dlp.data.api.responses.shop.chain.contact

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.models.shop.SimpleShopCity
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse

class Contacts(
    @SerializedName("phone")
    var phone: Int = 0,

    @SerializedName("link")
    var link: String = "",

    @SerializedName("fb_link")
    var fbLink: String? = null,

    @SerializedName("insta_link")
    var instagramLink: String? = null
)