package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class AuthenticationRequest(
    @SerializedName("send_sms") 
    val sendSms: Boolean,
    
    @SerializedName("mobile_phone") 
    val mobilePhone: String
) {

    class Builder {

        private var sendSms: Boolean = true
        private var mobilePhone: String = ""

        fun sendSms(init: () -> Boolean) {
            sendSms = init()
        }

        fun mobilePhone(init: () -> String) {
            mobilePhone = init()
        }

        fun build(init: Builder.() -> Unit): AuthenticationRequest {
            init()
            return AuthenticationRequest(sendSms, mobilePhone)
        }
    }
}

fun authenticationRequest(init: AuthenticationRequest.Builder.() -> Unit): AuthenticationRequest {
    return AuthenticationRequest.Builder().build(init)
}