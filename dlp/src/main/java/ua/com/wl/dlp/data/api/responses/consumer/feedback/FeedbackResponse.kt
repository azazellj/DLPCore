package ua.com.wl.dlp.data.api.responses.consumer.feedback

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class FeedbackResponse(
    @SerializedName("email") val email: String? = null,
    @SerializedName("mobile_phone") val phone: String? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("app_version") val appVersion: String? = null,
    @SerializedName("mobile_phone_info") val deviceInfo: String? = null)