package ua.com.wl.dlp.data.api.responses.consumer.history.notifications

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.consumer.history.notifications.NotificationData

@JsonClass(generateAdapter = true)
data class NotificationResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "type")
    val type: String?,

    @Json(name = "target_object_id")
    val objectId: Int,

    @Json(name = "target_object_content_type_id")
    val objectContentTypeId: Int,

    @Json(name = "title")
    val title: String?,

    @Json(name = "body")
    val body: String?,

    @Json(name = "title_loc_key")
    val titleLocKey: String?,

    @Json(name = "title_loc_args")
    val titleLocArgs: List<String>?,

    @Json(name = "body_loc_key")
    val bodyLocKey: String?,

    @Json(name = "body_loc_args")
    val bodyLocArgs: List<String>?,

    @Json(name = "sound")
    val sound: String?,

    @Json(name = "image_url")
    val imageUrl: String?,

    @Json(name = "click_action")
    val clickAction: String,

    @Json(name = "is_read")
    var isRead: Boolean,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "read_at")
    val markedAsReadAt: String?,

    @Json(name = "data")
    val data: NotificationData
)