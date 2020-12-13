package ua.com.wl.dlp.data.api.models.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimpleShopCity(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "timezone")
    val timezone: String?
)