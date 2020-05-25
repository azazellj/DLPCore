package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SignInRequest(
    @SerializedName("mobile_phone")
    val phone: String,

    @SerializedName("password")
    val password: String
) {

    class Builder {

        private var phone: String = ""
        private var password: String = ""

        fun phone(init: () -> String) {
            phone = init()
        }

        fun password(init: () -> String) {
            password = init()
        }

        fun build(init: Builder.() -> Unit): SignInRequest {
            init()
            return SignInRequest(phone, password)
        }
    }
}

fun signInRequest(init: SignInRequest.Builder.() -> Unit): SignInRequest {
    return SignInRequest.Builder().build(init)
}