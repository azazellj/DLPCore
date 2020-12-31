package ua.com.wl.dlp.data.api.responses.consumer.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.models.consumer.profile.ProfileOperation
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

@JsonClass(generateAdapter = true)
data class ProfileResponse(
    @Json(name = "first_name")
    val firstName: String,

    @Json(name = "patronymic")
    val patronymic: String,

    @Json(name = "last_name")
    val lastName: String,

    @Json(name = "gender")
    val gender: Gender?,

    @Json(name = "birth_date")
    val birthDate: String?,

    @Json(name = "is_married")
    val isMarried: Boolean?,

    @Json(name = "city")
    val city: City?,

    @Json(name = "address")
    val address: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "mobile_phone")
    val phone: String,

    @Json(name = "language")
    val language: String,

    @Json(name = "timezone")
    val timezone: String,

    @Json(name = "balance")
    val balance: Long,

    @Json(name = "balance_money")
    val moneyAmount: String,

    @Json(name = "invite_code")
    val inviteCode: String,

    @Json(name = "referral_code")
    val referralCode: String?,

    @Json(name = "comment")
    val comment: String,

    @Json(name = "notifications")
    val notifications: Int,

    @Json(name = "notification_token")
    val notificationToken: String,

    @Json(name = "balance_changes")
    val transactions: List<ProfileOperation>
)