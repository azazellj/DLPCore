package ua.com.wl.dlp.data.api.models.consumer.ranks

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Denis Makovskyi
 */
@JsonClass(generateAdapter = true)
data class RankSelectionCriteriaValue<C, R>(
    @Json(name = "current_value")
    val currentValue: C,

    @Json(name = "required_value")
    val requiredValue: R
)