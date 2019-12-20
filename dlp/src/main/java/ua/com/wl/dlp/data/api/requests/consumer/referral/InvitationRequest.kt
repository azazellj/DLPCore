package ua.com.wl.dlp.data.api.requests.consumer.referral

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class InvitationRequest constructor(
    @SerializedName("invite_code")
    val code: String)