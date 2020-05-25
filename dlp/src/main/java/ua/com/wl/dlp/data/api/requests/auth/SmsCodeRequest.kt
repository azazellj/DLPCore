package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SmsCodeRequest(
    @SerializedName("mobile_phone")
    val phone: String
) {

    class Builder {

        private var phone: String = ""

        fun phone(init: () -> String) {
            phone = init()
        }

        fun build(init: Builder.() -> Unit): SmsCodeRequest {
            init()
            return SmsCodeRequest(phone)
        }
    }
}

fun smsCodeRequest(init: SmsCodeRequest.Builder.() -> Unit): SmsCodeRequest {
    return SmsCodeRequest.Builder().build(init)
}