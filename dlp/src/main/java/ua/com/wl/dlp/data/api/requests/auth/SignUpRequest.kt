package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SignUpRequest(
    @SerializedName("city") 
    val city: Int,
    
    @SerializedName("mobile_phone") 
    val phone: String,
    
    @SerializedName("password")
    val password: String,
    
    @SerializedName("barcode") 
    val barcode: String? = null
) {

    class Builder {

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

        fun build(init: Builder.() -> Unit): SignUpRequest {
            init()
            return SignUpRequest(city, phone, password, barcode)
        }
    }
}

fun signUpRequest(init: SignUpRequest.Builder.() -> Unit): SignUpRequest {
    return SignUpRequest.Builder().build(init)
}