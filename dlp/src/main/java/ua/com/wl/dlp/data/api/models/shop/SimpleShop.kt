package ua.com.wl.dlp.data.api.models.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SimpleShop(
    @Json(name = "name")
    val name: String,

    @Json(name = "city")
    val city: SimpleShopCity
) : BaseSimpleShop()