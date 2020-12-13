package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Chain(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = ""
)