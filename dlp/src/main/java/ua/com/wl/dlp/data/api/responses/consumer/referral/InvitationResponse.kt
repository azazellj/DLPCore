package ua.com.wl.dlp.data.api.responses.consumer.referral

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class InvitationResponse constructor(
    @SerializedName("consumer_balance")
    val balance: Long,

    @SerializedName("invite_code")
    val inviteCode: String)