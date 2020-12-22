package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.auth.City

@JsonClass(generateAdapter = true)
data class ShopChainItem(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "is_pending")
    var isPending: Boolean = false,

    @Json(name = "thumb_logo")
    var thumbLogoUrl: String? = null,

    @Json(name = "schedule")
    var schedule: String = "",

    @Json(name = "address")
    var address: String? = null,

    @Json(name = "configuration")
    var configuration: ShopConfiguration? = null,

    @Json(name = "city")
    var city: City? = null)