package ua.com.wl.dlp.data.api.responses

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

class DataResponse<T>(type: String, @SerializedName("results") val payload: T) : BaseResponse(type)