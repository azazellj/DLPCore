package ua.com.wl.dlp.data.api.requests.orders.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class RateOrderRequest(
    @SerializedName("value")
    val value: Int,

    @SerializedName("comment")
    val comment: String = "")