package ua.com.wl.dlp.data.api.models.consumer.ranks

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class RankSelectionCriteriaValue<C, R>(
    @SerializedName("current_value")
    val currentValue: C,

    @SerializedName("required_value")
    val requiredValue: R)