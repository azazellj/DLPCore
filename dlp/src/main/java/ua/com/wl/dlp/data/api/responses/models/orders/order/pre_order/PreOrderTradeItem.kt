package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderTradeItem

/**
 * @author Denis Makovskyi
 */

data class PreOrderTradeItem(
    @SerializedName("price")
    val price: String,

    @SerializedName("offer")
    val offer: PreOrderOffer
) : BasePreOrderTradeItem()