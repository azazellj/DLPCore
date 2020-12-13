package ua.com.wl.dlp.data.api.models.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseSimpleShop(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "address")
    val address: String? = null,

    @Json(name = "thumb_logo")
    val thumbLogo: String? = null
)