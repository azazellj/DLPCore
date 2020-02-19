package ua.com.wl.dlp.data.api.responses.consumer.ranks

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.rank.RankPermissions

/**
 * @author Denis Makovskyi
 */

data class RankResponse(
    @SerializedName("description")
    val description: String,

    @SerializedName("permissions")
    val permissions: RankPermissions
): BaseRankResponse()