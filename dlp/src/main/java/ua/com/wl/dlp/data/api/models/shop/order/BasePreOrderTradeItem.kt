package ua.com.wl.dlp.data.api.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BasePreOrderTradeItem(
    @SerializedName("trade_item") val tradeItem: Int? = null,
    @SerializedName("count") val count: Int? = null)