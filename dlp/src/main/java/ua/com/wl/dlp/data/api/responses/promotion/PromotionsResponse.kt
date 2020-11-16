package ua.com.wl.dlp.data.api.responses.promotion

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.shop.chain.ShopChainItem

data class PromotionsResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("percent")
    var percent: String? = null,

    @SerializedName("is_disposable")
    var isDisposable: Boolean? = null,

    @SerializedName("criteria")
    var criteria: Criteria? = null,

    @SerializedName("offer")
    var offer: Offer? = null,

    @SerializedName("thumb_image")
    var thumbImage: String? = null,

    @SerializedName("shops")
    var relatedShops: List<ShopChainItem>? = null,

    @SerializedName("description")
    var description: String? = null
)