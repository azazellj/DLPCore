package ua.com.wl.dlp.data.api.responses.promotion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.shop.offer.item.OfferItem

@JsonClass(generateAdapter = true)
data class Offer(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "item")
    var item: OfferItem? = null,

    @Json(name = "short_description")
    var shortDescription: String? = null
)
