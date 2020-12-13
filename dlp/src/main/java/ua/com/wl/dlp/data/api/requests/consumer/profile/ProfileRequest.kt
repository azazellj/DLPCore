package ua.com.wl.dlp.data.api.requests.consumer.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

@JsonClass(generateAdapter = true)
data class ProfileRequest(
    @Json(name = "first_name")
    val firstName: String?,

    @Json(name = "patronymic")
    val patronymic: String?,

    @Json(name = "last_name")
    val lastName: String?,

    @Json(name = "gender")
    val gender: Gender?,

    @Json(name = "birth_date")
    val birthDate: String?,

    @Json(name = "is_married")
    val isMarried: Boolean?,

    @Json(name = "city")
    val city: Int?,

    @Json(name = "address")
    val address: String?,

    @Json(name = "email")
    val email: String?,

    @Json(name = "language")
    val language: String?,

    @Json(name = "comment")
    val comment: String?,

    @Json(name = "notification_token")
    val notificationToken: String?
)