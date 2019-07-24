package ua.com.wl.dlp.data.api.responses

import com.google.gson.annotations.SerializedName

open class BaseResponse(@SerializedName(value = "type", alternate = ["status"]) val type: String)