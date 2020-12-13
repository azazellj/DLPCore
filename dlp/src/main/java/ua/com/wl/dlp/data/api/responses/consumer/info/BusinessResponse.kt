package ua.com.wl.dlp.data.api.responses.consumer.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusinessResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "phone")
    val phone: String?,

    @Json(name = "email")
    val email: String?,

    @Json(name = "address")
    val address: String?,

    @Json(name = "home_page")
    val homePage: String?,

    @Json(name = "feedback_email")
    val feedbackEmail: String?,

    @Json(name = "app_link")
    val applicationLink: String?
)