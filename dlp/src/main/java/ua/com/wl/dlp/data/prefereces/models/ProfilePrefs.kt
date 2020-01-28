package ua.com.wl.dlp.data.prefereces.models

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

/**
 * @author Denis Makovskyi
 */

data class ProfilePrefs constructor(
    @SerializedName("first_name")
    val firstName: String? = null,

    @SerializedName("patronymic")
    val patronymic: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("gender")
    val gender: Gender? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("mobile_phone")
    val phone: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("city")
    val city: City? = null,

    @SerializedName("birth_date")
    val birthDate: String? = null,

    @SerializedName("is_married")
    val isMarried: Boolean? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("timezone")
    val timezone: String? = null,

    @SerializedName("balance")
    val balance: Long? = null,

    @SerializedName("money_amount")
    val moneyAmount: String? = null,

    @SerializedName("qr_code")
    val qrCode: String? = null,

    @SerializedName("invite_code")
    val inviteCode: String? = null,

    @SerializedName("referral_code")
    val referralCode: String? = null,

    @SerializedName("comment")
    val comment: String? = null)