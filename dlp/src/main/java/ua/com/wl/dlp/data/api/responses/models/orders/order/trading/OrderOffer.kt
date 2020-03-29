package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderOffer(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("thumb_image")
    val thumbImage: String?)