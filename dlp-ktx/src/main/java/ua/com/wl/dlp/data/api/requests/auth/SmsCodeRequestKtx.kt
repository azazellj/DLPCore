package ua.com.wl.dlp.data.api.requests.auth

class SmsCodeRequestBuilder {
    private var phone: String = ""

    fun phone(init: () -> String) {
        phone = init()
    }

    fun build(init: SmsCodeRequestBuilder.() -> Unit): SmsCodeRequest {
        init()
        return SmsCodeRequest(phone)
    }
}

fun smsCodeRequest(init: SmsCodeRequestBuilder.() -> Unit): SmsCodeRequest {
    return SmsCodeRequestBuilder().build(init)
}