package ua.com.wl.dlp.data.api.requests.consumer.referral

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InvitationRequest(
    @Json(name = "invite_code")
    val code: String
)