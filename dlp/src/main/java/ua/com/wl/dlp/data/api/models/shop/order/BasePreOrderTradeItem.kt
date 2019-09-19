package ua.com.wl.dlp.data.api.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BasePreOrderTradeItem(
    @SerializedName("count")
    var count: Int = 0,

    @SerializedName("trade_item")
    var tradeItem: Int = 0)