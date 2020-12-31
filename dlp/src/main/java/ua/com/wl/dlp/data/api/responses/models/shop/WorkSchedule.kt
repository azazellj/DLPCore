package ua.com.wl.dlp.data.api.responses.models.shop

import androidx.annotation.IntRange

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkSchedule(
    @Json(name = "id")
    val id: Int,

    @IntRange(from = 0L, to = 8L)
    @Json(name = "week_day")
    val weekDay: Int,

    @Json(name = "time_from")
    val timeFrom: String,

    @Json(name = "time_to")
    val timeUntil: String
)