package ua.com.wl.dlp.data.api.requests.consumer.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteProfileRequest(
    @Json(name = "code")
    val code: String
)