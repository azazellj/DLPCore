package ua.com.wl.dlp.data.api.responses.models.another

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NoveltyDatesRange(
    @Json(name = "lower")
    val lower: String,

    @Json(name = "upper")
    val upper: String
)