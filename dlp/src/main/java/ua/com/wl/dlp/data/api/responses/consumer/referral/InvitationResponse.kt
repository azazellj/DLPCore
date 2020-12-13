package ua.com.wl.dlp.data.api.responses.consumer.referral

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InvitationResponse(
    @Json(name = "consumer_balance")
    val balance: Long,

    @Json(name = "invite_code")
    val inviteCode: String
)