package ua.com.wl.dlp.data.api.responses.consumer.info

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class BusinessResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("address")
    val address: String?,

    @SerializedName("home_page")
    val homePage: String?,

    @SerializedName("feedback_email")
    val feedbackEmail: String?,

    @SerializedName("address")
    val applicationLink: String?)