package ua.com.wl.dlp.data.api.requests.auth

class CardsStatusRequestBuilder {
    private var phone: String = ""
    private var password: String = ""

    fun phone(init: () -> String) {
        phone = init()
    }

    fun password(init: () -> String) {
        password = init()
    }

    fun build(init: CardsStatusRequestBuilder.() -> Unit): CardsStatusRequest {
        init()
        return CardsStatusRequest(phone, password)
    }
}

fun cardStatusRequest(init: CardsStatusRequestBuilder.() -> Unit): CardsStatusRequest {
    return CardsStatusRequestBuilder().build(init)
}