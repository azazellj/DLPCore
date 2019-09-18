package ua.com.wl.dlp.data.api.responses.consumer.feedback

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class FeedbackResponse(
    @SerializedName("email") val email: String,
    @SerializedName("mobile_phone") val phone: String,
    @SerializedName("message") val message: String,
    @SerializedName("app_version") val appVersion: String,
    @SerializedName("mobile_phone_info") val deviceInfo: String)