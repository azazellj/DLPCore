package ua.com.wl.dlp.data.api.requests.consumer.history.notifications

class NotificationsReadRequestBuilder {
    private val items = mutableListOf<Int>()

    fun item(init: () -> Int) {
        items.add(init())
    }

    fun build(init: NotificationsReadRequestBuilder.() -> Unit): NotificationsReadRequest {
        init()
        return NotificationsReadRequest(items)
    }
}

fun notificationsReadRequest(init: NotificationsReadRequestBuilder.() -> Unit): NotificationsReadRequest {
    return NotificationsReadRequestBuilder().build(init)
}