package ua.com.wl.dlp.data.api.requests.consumer.history.notifications

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class NotificationsReadRequest(
    @SerializedName("items")
    val items: List<Int>
) {

    class Builder {

        private val items = mutableListOf<Int>()

        fun item(init: () -> Int) {
            items.add(init())
        }

        fun build(init: Builder.() -> Unit): NotificationsReadRequest {
            init()
            return NotificationsReadRequest(items)
        }
    }
}

fun notificationsReadRequest(init: NotificationsReadRequest.Builder.() -> Unit): NotificationsReadRequest {
    return NotificationsReadRequest.Builder().build(init)
}