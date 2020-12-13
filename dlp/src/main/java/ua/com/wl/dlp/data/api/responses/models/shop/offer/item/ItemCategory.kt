package ua.com.wl.dlp.data.api.responses.models.shop.offer.item

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemCategory(
    @Json(name = "id")
    val id: Int,

    @Json(name = "parent_id")
    val parentId: Int?,

    @Json(name = "name")
    val name: String,

    @Json(name = "timezone")
    val timezone: String?
)