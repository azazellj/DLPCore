package ua.com.wl.dlp.data.api.responses.models.shop

import androidx.annotation.IntRange

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */
data class WorkSchedule(
    @SerializedName("id")
    val id: Int,

    @IntRange(from = 0L, to = 8L)
    @SerializedName("week_day")
    val weekDay: Int?,

    @SerializedName("time_from")
    val timeFrom: String,

    @SerializedName("time_to")
    val timeUntil: String)