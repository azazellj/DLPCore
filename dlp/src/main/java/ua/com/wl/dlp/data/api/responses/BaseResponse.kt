package ua.com.wl.dlp.data.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseResponse(
    @Json(name = "type")
    open val responseType: String?,
    @Json(name = "status")
    open val status: String?
) {
    val type: String
        get() {
            return responseType ?: status.orEmpty()
        }

    fun isSuccessfully() = type.equals(ResponseType.OK, true)
}