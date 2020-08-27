package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.google.gson.annotations.SerializedName

class Chain(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "")