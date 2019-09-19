package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class CardsStatusRequest(
    @SerializedName("phone_number") 
    val phone: String,
    
    @SerializedName("sms_code") 
    val smsCode: String)