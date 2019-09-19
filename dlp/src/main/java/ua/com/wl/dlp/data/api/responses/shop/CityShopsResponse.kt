package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class CityShopsResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("is_native_city")
    val isNativeCity: Boolean,

    @SerializedName("shops")
    val shops: List<BaseShopResponse>)