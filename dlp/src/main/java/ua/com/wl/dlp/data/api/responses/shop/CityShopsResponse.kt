package ua.com.wl.dlp.data.api.responses.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityShopsResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "is_native_city")
    val isNativeCity: Boolean,

    @Json(name = "shops")
    val shops: List<ShopResponse>
)