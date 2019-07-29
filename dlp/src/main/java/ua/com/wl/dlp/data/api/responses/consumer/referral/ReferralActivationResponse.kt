package ua.com.wl.dlp.data.api.responses.consumer.referral

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class ReferralActivationResponse(
    @SerializedName("invite_code") val inviteCode: String,
    @SerializedName("consumer_balance") val consumerBalance: Long)