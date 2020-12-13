package ua.com.wl.dlp.data.api.requests.auth

class SignUpRequestBuilder {
    private var city: Int = 0
    private var phone: String = ""
    private var password: String = ""
    private var barcode: String? = null

    fun city(init: () -> Int) {
        city = init()
    }

    fun phone(init: () -> String) {
        phone = init()
    }

    fun password(init: () -> String) {
        password = init()
    }

    fun barcode(init: () -> String?) {
        barcode = init()
    }

    fun build(init: SignUpRequestBuilder.() -> Unit): SignUpRequest {
        init()
        return SignUpRequest(city, phone, password, barcode)
    }
}

fun signUpRequest(init: SignUpRequestBuilder.() -> Unit): SignUpRequest {
    return SignUpRequestBuilder().build(init)
}