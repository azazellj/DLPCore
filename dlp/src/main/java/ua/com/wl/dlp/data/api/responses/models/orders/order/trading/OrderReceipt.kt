package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product.OrderItem

@JsonClass(generateAdapter = true)
data class OrderReceipt(
    @Json(name = "total_price_in_money")
    val totalPriceInMoney: String,

    @Json(name = "total_price_in_bonuses")
    val totalPriceInBonuses: Long,

    @Json(name = "total_price_in_bonuses_money")
    val totalPriceInBonusesMoney: String,

    @Json(name = "items")
    val items: List<OrderItem>?,

    @Json(name = "trade_items")
    val tradeItems: List<OrderTradeItem>?
)