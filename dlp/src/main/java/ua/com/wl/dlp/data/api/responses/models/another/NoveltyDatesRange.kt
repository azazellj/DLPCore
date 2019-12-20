package ua.com.wl.dlp.data.api.responses.models.another

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class NoveltyDatesRange constructor(
    @SerializedName("lower")
    val lower: String,

    @SerializedName("upper")
    val upper: String)