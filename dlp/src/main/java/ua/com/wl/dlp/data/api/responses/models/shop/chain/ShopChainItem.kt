package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.auth.City

class ShopChainItem(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("is_pending")
    var isPending: Boolean = false,

    @SerializedName("thumb_logo")
    var thumbLogoUrl: String? = null,

    @SerializedName("schedule")
    var schedule: String = "",

    @SerializedName("address")
    var address: String? = null,

    @SerializedName("configuration")
    var configuration: ShopConfiguration? = null,

    @SerializedName("city")
    var city: City? = null)