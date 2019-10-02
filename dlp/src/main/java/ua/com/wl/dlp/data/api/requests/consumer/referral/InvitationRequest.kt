package ua.com.wl.dlp.data.api.requests.consumer.referral

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class InvitationRequest(@SerializedName("invite_code") val code: String)