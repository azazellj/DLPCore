package ua.com.wl.dlp.data.api.responses.shop.chain

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.models.shop.SimpleShopCity
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.chain.contact.Contacts

class StoreChainResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("shops")
    var shops: List<ShopResponse>? = null,

    @SerializedName("contacts")
    var contacts: Contacts? = null
)