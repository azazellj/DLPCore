package ua.com.wl.dlp.data.api.requests.consumer.profile

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

/**
 * @author Denis Makovskyi
 */

data class ProfileRequest(
    @SerializedName("first_name")
    val firstName: String?,

    @SerializedName("patronymic")
    val patronymic: String?,

    @SerializedName("last_name")
    val lastName: String?,

    @SerializedName("gender")
    val gender: Gender?,

    @SerializedName("birth_date")
    val birthDate: String?,

    @SerializedName("is_married")
    val isMarried: Boolean?,

    @SerializedName("city")
    val city: Int?,

    @SerializedName("address")
    val address: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("notification_token")
    val notificationToken: String?
) {

    class Builder {

        private var firstName: String? = null
        private var patronymic: String? = null
        private var lastName: String? = null
        private var gender: Gender? = null
        private var birthDate: String? = null
        private var isMarried: Boolean? = null
        private var city: Int? = null
        private var address: String? = null
        private var email: String? = null
        private var language: String? = null
        private var comment: String? = null
        private var notificationToken: String? = null

        fun firstName(init: () -> String?) {
            firstName = init() ?: return
        }

        fun patronymic(init: () -> String?) {
            patronymic = init() ?: return
        }

        fun lastName(init: () -> String?) {
            lastName = init() ?: return
        }

        fun gender(init: () -> Gender?) {
            gender = init() ?: return
        }

        fun birthDate(init: () -> String?) {
            birthDate = init() ?: return
        }

        fun isMarried(init: () -> Boolean?) {
            isMarried = init() ?: return
        }

        fun city(init: () -> Int?) {
            city = init() ?: return
        }

        fun address(init: () -> String?) {
            address = init() ?: return
        }

        fun email(init: () -> String?) {
            email = init() ?: return
        }

        fun language(init: () -> String?) {
            language = init() ?: return
        }

        fun comment(init: () -> String?) {
            comment = init() ?: return
        }

        fun notificationToken(init: () -> String?) {
            notificationToken = init() ?: return
        }

        fun build(init: Builder.() -> Unit): ProfileRequest {
            init()
            return ProfileRequest(
                firstName, patronymic, lastName,
                gender, birthDate, isMarried,
                city, address, email,
                language, comment, notificationToken)
        }
    }
}

fun profileRequest(init: ProfileRequest.Builder.() -> Unit): ProfileRequest {
    return ProfileRequest.Builder().build(init)
}