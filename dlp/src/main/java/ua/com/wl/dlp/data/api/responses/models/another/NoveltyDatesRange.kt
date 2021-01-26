package ua.com.wl.dlp.data.api.responses.models.another

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class NoveltyDatesRange(
    @Json(name = "lower")
    val lower: String,

    @Json(name = "upper")
    val upper: String
) : Parcelable