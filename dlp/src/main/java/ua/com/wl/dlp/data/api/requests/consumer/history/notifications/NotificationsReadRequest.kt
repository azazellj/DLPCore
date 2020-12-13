package ua.com.wl.dlp.data.api.requests.consumer.history.notifications

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationsReadRequest(
    @Json(name = "items")
    val items: List<Int>
)