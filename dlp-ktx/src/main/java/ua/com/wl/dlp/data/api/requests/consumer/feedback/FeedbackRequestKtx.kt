package ua.com.wl.dlp.data.api.requests.consumer.feedback

class FeedbackRequestBuilder {
    private var phone: String? = null
    private var email: String? = null
    private var message: String? = null
    private var appVersion: String? = null
    private var deviceInfo: String? = null

    fun phone(init: () -> String?) {
        phone = init()
    }

    fun email(init: () -> String?) {
        email = init()
    }

    fun message(init: () -> String?) {
        message = init()
    }

    fun appVersion(init: () -> String?) {
        appVersion = init()
    }

    fun deviceInfo(init: () -> String?) {
        deviceInfo = init()
    }

    fun build(init: FeedbackRequestBuilder.() -> Unit): FeedbackRequest {
        init()
        return FeedbackRequest(phone, email, message, appVersion, deviceInfo)
    }
}

fun feedbackRequest(init: FeedbackRequestBuilder.() -> Unit): FeedbackRequest {
    return FeedbackRequestBuilder().build(init)
}