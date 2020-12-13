package ua.com.wl.dlp.data.prefereces.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

@JsonClass(generateAdapter = true)
data class ProfilePrefs(
    @Json(name = "first_name")
    val firstName: String? = null,

    @Json(name = "patronymic")
    val patronymic: String? = null,

    @Json(name = "last_name")
    val lastName: String? = null,

    @Json(name = "gender")
    val gender: Gender? = null,

    @Json(name = "birth_date")
    val birthDate: String? = null,

    @Json(name = "is_married")
    val isMarried: Boolean? = null,

    @Json(name = "city")
    val city: City? = null,

    @Json(name = "address")
    val address: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "mobile_phone")
    val phone: String? = null,

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "timezone")
    val timezone: String? = null,

    @Json(name = "balance")
    val balance: Long? = null,

    @Json(name = "money_amount")
    val moneyAmount: String? = null,

    @Json(name = "qr_code")
    val qrCode: String? = null,

    @Json(name = "invite_code")
    val inviteCode: String? = null,

    @Json(name = "referral_code")
    val referralCode: String? = null,

    @Json(name = "comment")
    val comment: String? = null
)