package ua.com.wl.dlp.data.api.requests.consumer.feedback

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class FeedbackRequest(
    @SerializedName("message") val message: String,
    @SerializedName("mobile_app_version") val appVersion: String,
    @SerializedName("mobile_phone") val phone: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("mobile_phone_info") val deviceInfo: String? = null) {

    internal class Builder {
        var message: String? = null
        var appVersion: String? = null
        var email: String? = null
        var phone: String? = null
        var deviceInfo: String? = null

        fun build(): FeedbackRequest =
            FeedbackRequest(
                requireNotNull(message),
                requireNotNull(appVersion),
                phone, email, deviceInfo)
    }
}

internal fun feedback(init: FeedbackRequest.Builder.() -> Unit): FeedbackRequest = FeedbackRequest.Builder().apply(init).build()