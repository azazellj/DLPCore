package ua.com.wl.dlp.data.api.responses.consumer.shop

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.shop.Shop

/**
 * @author Denis Makovskyi
 */

data class ShopsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("is_native_city") val isNativeCity: Boolean,
    @SerializedName("shops") val shops: List<Shop>)