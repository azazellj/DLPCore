package ua.com.wl.dlp.data.prefereces.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusinessPrefs(
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "phone")
    val phone: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "address")
    val address: String? = null,

    @Json(name = "home_page")
    val homePage: String? = null,

    @Json(name = "feedback_email")
    val feedbackEmail: String? = null,

    @Json(name = "app_link")
    val applicationLink: String? = null
)