package ua.com.wl.dlp.data.api.requests.auth

class AuthenticationRequestBuilder {
    private var sendSms: Boolean = true
    private var mobilePhone: String = ""

    fun sendSms(init: () -> Boolean) {
        sendSms = init()
    }

    fun mobilePhone(init: () -> String) {
        mobilePhone = init()
    }

    fun build(init: AuthenticationRequestBuilder.() -> Unit): AuthenticationRequest {
        init()
        return AuthenticationRequest(sendSms, mobilePhone)
    }
}

fun authenticationRequest(init: AuthenticationRequestBuilder.() -> Unit): AuthenticationRequest {
    return AuthenticationRequestBuilder().build(init)
}