package ua.com.wl.dlp.data.api.requests.auth

class SignInRequestBuilder {
    private var phone: String = ""
    private var password: String = ""

    fun phone(init: () -> String) {
        phone = init()
    }

    fun password(init: () -> String) {
        password = init()
    }

    fun build(init: SignInRequestBuilder.() -> Unit): SignInRequest {
        init()
        return SignInRequest(phone, password)
    }
}

fun signInRequest(init: SignInRequestBuilder.() -> Unit): SignInRequest {
    return SignInRequestBuilder().build(init)
}