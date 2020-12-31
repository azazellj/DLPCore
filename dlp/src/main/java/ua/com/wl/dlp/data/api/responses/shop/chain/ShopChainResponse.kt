package ua.com.wl.dlp.data.api.responses.shop.chain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.shop.chain.Contacts
import ua.com.wl.dlp.data.api.responses.models.shop.chain.ShopChainItem

@JsonClass(generateAdapter = true)
class ShopChainResponse(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "image")
    var image: String? = null,

    @Json(name = "description")
    var description: String? = null,

    @Json(name = "contacts")
    var chainContacts: Contacts? = null,

    @Json(name = "shops")
    var relatedShops: List<ShopChainItem>? = null
)