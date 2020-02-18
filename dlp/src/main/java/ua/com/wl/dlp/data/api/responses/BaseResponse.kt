package ua.com.wl.dlp.data.api.responses

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BaseResponse(
    @SerializedName(value = "type", alternate = ["status"])
    val type: String
) {

    fun isSuccessfully() = type.equals(ResponseType.OK, true)
}