package ua.com.wl.dlp.data.api.responses.models.orders.order.rate

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderRating(
    @SerializedName("value")
    val value: Int,

    @SerializedName("comment")
    val comment: String,

    @SerializedName("is_viewed")
    val isViewed: Boolean)