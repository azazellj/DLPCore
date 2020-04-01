package ua.com.wl.dlp.data.api.responses.consumer.history.notifications

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.notifications.NotificationData

/**
 * @author Denis Makovskyi
 */

data class NotificationResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("type")
    val type: String?,

    @SerializedName("target_object_id")
    val objectId: Int,

    @SerializedName("target_object_content_type_id")
    val objectContentTypeId: Int,

    @SerializedName("title")
    val title: String?,

    @SerializedName("body")
    val body: String?,

    @SerializedName("title_loc_key")
    val titleLocKey: String?,

    @SerializedName("title_loc_args")
    val titleLocArgs: List<String>?,

    @SerializedName("body_loc_key")
    val bodyLocKey: String?,

    @SerializedName("body_loc_args")
    val bodyLocArgs: List<String>?,

    @SerializedName("sound")
    val sound: String?,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("click_action")
    val clickAction: String,

    @SerializedName("is_read")
    val isRead: Boolean,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("read_at")
    val markedAsReadAt: String?,

    @SerializedName("data")
    val data: NotificationData)