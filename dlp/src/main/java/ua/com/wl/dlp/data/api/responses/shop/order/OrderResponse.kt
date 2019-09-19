package ua.com.wl.dlp.data.api.responses.shop.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.order.OrderTradeItem

/**
 * @author Denis Makovskyi
 */

data class OrderResponse(
    @SerializedName("order_trade_items")
    val tradeItems: List<OrderTradeItem>): BaseOrderResponse()