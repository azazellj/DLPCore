package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PreOrderReceipt(
    @SerializedName("count")
    val count: Int,

    @SerializedName("price")
    val price: String,

    @SerializedName("offer")
    val offer: PreOrderOffer)