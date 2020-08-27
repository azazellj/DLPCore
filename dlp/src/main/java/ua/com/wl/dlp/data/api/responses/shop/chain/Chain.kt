package ua.com.wl.dlp.data.api.responses.shop.chain

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.models.shop.SimpleShopCity
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.chain.contact.Contacts

class Chain(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = ""

)