package ua.com.wl.dlp.data.api.responses.models.another

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class NoveltyDatesRange(
    @SerializedName("lower") val lower: String?,
    @SerializedName("lower") val upper: String?)