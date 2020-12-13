package ua.com.wl.dlp.data.api.responses.models.shop.service

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopService(
    @Json(name = "name")
    val name: String,

    @Json(name = "thumb_icon")
    val thumbIcon: String?
)