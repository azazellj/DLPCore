package ua.com.wl.dlp.data.api.requests.consumer.feedback

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class FeedbackRequest(
    @SerializedName("mobile_phone")
    val phone: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("mobile_app_version")
    val appVersion: String?,

    @SerializedName("mobile_phone_info")
    val deviceInfo: String?
) {

    class Builder {

        private var phone: String? = null
        private var email: String? = null
        private var message: String? = null
        private var appVersion: String? = null
        private var deviceInfo: String? = null

        fun phone(init: () -> String?) {
            phone = init() ?: return
        }

        fun email(init: () -> String?) {
            email = init() ?: return
        }

        fun message(init: () -> String?) {
            message = init() ?: return
        }

        fun appVersion(init: () -> String?) {
            appVersion = init() ?: return
        }

        fun deviceInfo(init: () -> String?) {
            deviceInfo = init() ?: return
        }

        fun build(init: Builder.() -> Unit): FeedbackRequest {
            init()
            return FeedbackRequest(phone, email, message, appVersion, deviceInfo)
        }
    }
}

fun feedbackRequest(init: FeedbackRequest.Builder.() -> Unit): FeedbackRequest {
    return FeedbackRequest.Builder().build(init)
}