package ua.com.wl.dlp.data.api.responses.promotion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.shop.chain.ShopChainItem

@JsonClass(generateAdapter = true)
data class PromotionsResponse(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "type")
    var type: String? = null,

    @Json(name = "percent")
    var percent: String? = null,

    @Json(name = "is_disposable")
    var isDisposable: Boolean? = null,

    @Json(name = "criteria")
    var criteria: Criteria? = null,

    @Json(name = "offer")
    var offer: Offer? = null,

    @Json(name = "thumb_image")
    var thumbImage: String? = null,

    @Json(name = "shops")
    var relatedShops: List<ShopChainItem>? = null,

    @Json(name = "description")
    var description: String? = null
)