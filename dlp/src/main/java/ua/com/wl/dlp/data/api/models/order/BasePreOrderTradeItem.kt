package ua.com.wl.dlp.data.api.models.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BasePreOrderTradeItem constructor(
    @SerializedName("count")
    var count: Int = 0,

    @SerializedName("trade_item")
    var tradeItem: Int = 0
) {

    fun count(init: () -> Int) {
        count = init()
    }

    fun tradeItem(init: () -> Int) {
        tradeItem = init()
    }
}

fun tradeItem(
    init: BasePreOrderTradeItem.() -> Unit
): BasePreOrderTradeItem = BasePreOrderTradeItem().apply(init)