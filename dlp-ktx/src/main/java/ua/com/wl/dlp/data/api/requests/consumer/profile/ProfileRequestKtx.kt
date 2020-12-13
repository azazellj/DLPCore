package ua.com.wl.dlp.data.api.requests.consumer.profile

import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

class ProfileRequestBuilder {
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
        firstName = init()
    }

    fun patronymic(init: () -> String?) {
        patronymic = init()
    }

    fun lastName(init: () -> String?) {
        lastName = init()
    }

    fun gender(init: () -> Gender?) {
        gender = init()
    }

    fun birthDate(init: () -> String?) {
        birthDate = init()
    }

    fun isMarried(init: () -> Boolean?) {
        isMarried = init()
    }

    fun city(init: () -> Int?) {
        city = init()
    }

    fun address(init: () -> String?) {
        address = init()
    }

    fun email(init: () -> String?) {
        email = init()
    }

    fun language(init: () -> String?) {
        language = init()
    }

    fun comment(init: () -> String?) {
        comment = init()
    }

    fun notificationToken(init: () -> String?) {
        notificationToken = init()
    }

    fun build(init: ProfileRequestBuilder.() -> Unit): ProfileRequest {
        init()
        return ProfileRequest(
            firstName, patronymic, lastName,
            gender, birthDate, isMarried,
            city, address, email,
            language, comment, notificationToken
        )
    }
}

fun profileRequest(init: ProfileRequestBuilder.() -> Unit): ProfileRequest {
    return ProfileRequestBuilder().build(init)
}