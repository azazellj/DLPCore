package ua.com.wl.dlp.data.api.responses.models.shop.service

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class ShopService(
    @SerializedName("name") val name: String,
    @SerializedName("thumb_icon") val thumbIcon: String?)