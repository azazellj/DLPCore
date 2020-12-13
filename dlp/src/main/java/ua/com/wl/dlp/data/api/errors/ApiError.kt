package ua.com.wl.dlp.data.api.errors

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "type")
    val errorType: String?,
    @Json(name = "status")
    val status: String?
) {
    val type: String
        get() {
            return errorType ?: status.orEmpty()
        }
}