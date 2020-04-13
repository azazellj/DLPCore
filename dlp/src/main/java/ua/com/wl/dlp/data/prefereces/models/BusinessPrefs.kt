package ua.com.wl.dlp.data.prefereces.models

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class BusinessPrefs(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("address")
    val homePage: String? = null,

    @SerializedName("feedback_email")
    val feedbackEmail: String? = null,

    @SerializedName("address")
    val applicationLink: String? = null)