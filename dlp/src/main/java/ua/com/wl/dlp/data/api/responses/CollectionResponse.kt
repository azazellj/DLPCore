package ua.com.wl.dlp.data.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionResponse<T>(
    @Json(name = "data")
    val data: List<T> = emptyList(),
    @Json(name = "results")
    val results: List<T> = emptyList()
) {
    val items: List<T> = if (data.isNotEmpty()) data else results
}