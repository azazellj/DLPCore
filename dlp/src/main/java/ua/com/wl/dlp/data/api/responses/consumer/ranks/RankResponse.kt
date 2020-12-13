package ua.com.wl.dlp.data.api.responses.consumer.ranks

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.consumer.rank.RankPermissions
import ua.com.wl.dlp.data.api.responses.models.consumer.rank.RankSelectionCriteria

@JsonClass(generateAdapter = true)
open class RankResponse(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "rank_priority_number")
    var priority: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "thumb_icon")
    var iconUrl: String = "",

    @Json(name = "color")
    var colorHex: String = "",

    @Json(name = "description")
    var description: String = "",

    @Json(name = "is_next_rank")
    var isNext: Boolean = false,

    @Json(name = "is_current_rank")
    var isCurrent: Boolean = false,

    @Json(name = "is_previous_rank")
    var isPrevious: Boolean = false,

    @Transient
    var wasReached: Boolean = false,

    @Json(name = "permissions")
    val permissions: RankPermissions? = null,

    @Json(name = "selection_criteria")
    var selectionCriteria: RankSelectionCriteria? = null
)