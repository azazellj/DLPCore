package ua.com.wl.dlp.data.api.responses.consumer.profile

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

/**
 * @author Denis Makovskyi
 */

data class ProfileResponse constructor(
    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("patronymic")
    val patronymic: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("gender")
    val gender: Gender?,

    @SerializedName("email")
    val email: String,

    @SerializedName("mobile_phone")
    val phone: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("city")
    val city: City?,

    @SerializedName("birth_date")
    val birthDate: String?,

    @SerializedName("is_married")
    val isMarried: Boolean?,

    @SerializedName("language")
    val language: String,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("balance")
    val balance: Long,

    @SerializedName("invite_code")
    val inviteCode: String,

    @SerializedName("referral_code")
    val referralCode: String?,

    @SerializedName("comment")
    val comment: String,

    @SerializedName("notifications")
    val notifications: Int,

    @SerializedName("notification_token")
    val notificationToken: String,

    @SerializedName("balance_changes")
    val transactions: List<TransactionResponse>)