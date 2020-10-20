package ua.com.wl.dlp.data.api.responses.promotion

import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",
)
