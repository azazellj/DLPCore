package ua.com.wl.dlp.data.api.responses.promotion

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.shop.offer.item.OfferItem

data class Offer(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("item")
    var item: OfferItem? = null,

    @SerializedName("short_description")
    var shortDescription: String? = null
)
