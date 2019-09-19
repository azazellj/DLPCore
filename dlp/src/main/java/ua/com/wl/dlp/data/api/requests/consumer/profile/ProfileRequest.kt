package ua.com.wl.dlp.data.api.requests.consumer.profile

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

/**
 * @author Denis Makovskyi
 */

data class ProfileRequest(
    @SerializedName("first_name")
    var firstName: String? = null,

    @SerializedName("patronymic")
    var patronymic: String? = null,

    @SerializedName("last_name")
    var lastName: String? = null,

    @SerializedName("gender")
    var gender: Gender? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("city")
    var city: Int? = null,

    @SerializedName("address")
    var address: String? = null,

    @SerializedName("birth_date")
    var birthDate: String? = null,

    @SerializedName("is_married")
    var isMarried: Boolean? = null,

    @SerializedName("language")
    var language: String? = null,

    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("notification_token")
    var notificationToken: String? = null)

fun profile(
    init: ProfileRequest.() -> Unit
): ProfileRequest = ProfileRequest().apply(init)