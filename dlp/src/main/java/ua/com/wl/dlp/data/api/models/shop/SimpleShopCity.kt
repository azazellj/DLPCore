package ua.com.wl.dlp.data.api.models.shop

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SimpleShopCity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("timezone")
    val timezone: String)