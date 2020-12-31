package ua.com.wl.dlp.data.api.responses.models.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
)