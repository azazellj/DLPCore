package ua.com.wl.dlp.data.api.responses.shop.chain

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.chain.Contacts
import ua.com.wl.dlp.data.api.responses.models.shop.chain.ShopChainItem

class ShopChainResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("contacts")
    var chainContacts: Contacts? = null,

    @SerializedName("shops")
    var relatedShops: List<ShopChainItem>? = null)