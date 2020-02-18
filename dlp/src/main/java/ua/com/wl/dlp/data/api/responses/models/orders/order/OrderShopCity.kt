package ua.com.wl.dlp.data.api.responses.models.orders.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderShopCity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("timezone")
    val timezone: String)