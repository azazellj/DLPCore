package ua.com.wl.dlp.data.api.responses

import com.google.gson.annotations.SerializedName

class DataResponse<T>(type: String, @SerializedName("results") val data: T) : BaseResponse(type)