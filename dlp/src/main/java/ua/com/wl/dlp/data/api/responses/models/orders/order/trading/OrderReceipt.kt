package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product.OrderItem

/**
 * @author Denis Makovskyi
 */

data class OrderReceipt(
    @SerializedName("total_price_in_money")
    val totalPriceInMoney: String,

    @SerializedName("total_price_in_bonuses")
    val totalPriceInBonuses: Long,

    @SerializedName("total_price_in_bonuses_money")
    val totalPriceInBonusesMoney: String,

    @SerializedName("items")
    val items: List<OrderItem>?,

    @SerializedName("trade_items")
    val tradeItems: List<OrderTradeItem>?)