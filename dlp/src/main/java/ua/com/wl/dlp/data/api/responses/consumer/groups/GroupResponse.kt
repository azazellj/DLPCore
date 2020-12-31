package ua.com.wl.dlp.data.api.responses.consumer.groups

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.models.shop.SimpleShop

@JsonClass(generateAdapter = true)
data class GroupResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "cash_back_percentage")
    val cashBackPercentage: Int,

    @Json(name = "shop")
    val orderShop: SimpleShop
)