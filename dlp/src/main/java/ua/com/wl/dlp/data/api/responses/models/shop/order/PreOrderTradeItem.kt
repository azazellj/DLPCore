package ua.com.wl.dlp.data.api.responses.models.shop.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.shop.order.BasePreOrderTradeItem

/**
 * @author Denis Makovskyi
 */

data class PreOrderTradeItem(
    @SerializedName("price")
    val price: String,

    @SerializedName("offer")
    val offer: PreOrderOffer) : BasePreOrderTradeItem()