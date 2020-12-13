package ua.com.wl.dlp.data.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DataResponse<T>(
    @Json(name = "type")
    override val responseType: String?,
    @Json(name = "status")
    override val status: String?,
    @Json(name = "results")
    val payload: T
) : BaseResponse(responseType, status)