package ua.com.wl.dlp.data.api.responses.consumer.history.notifications

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.PagedResponse

/**
 * @author Denis Makovskyi
 */

data class NotificationsResponse(
    @SerializedName("notification_count")
    val notificationsCount: Int,

    @SerializedName("notification_read_count")
    val notificationsReadCount: Int,

    @SerializedName("notification_unread_count")
    val notificationsUnreadCount: Int
) : PagedResponse<NotificationResponse>()