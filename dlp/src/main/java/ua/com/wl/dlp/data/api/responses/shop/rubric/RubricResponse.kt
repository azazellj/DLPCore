package ua.com.wl.dlp.data.api.responses.shop.rubric

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RubricResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "thumb_image")
    val thumbImage: String?
)