package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class AuthenticationRequest(
    @SerializedName("send_sms") 
    val sendSms: Boolean = true,
    
    @SerializedName("mobile_phone") 
    val mobilePhone: String)