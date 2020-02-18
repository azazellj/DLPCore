package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.BaseOrderShop
import ua.com.wl.dlp.data.api.responses.models.orders.order.ConsumerOrder
import ua.com.wl.dlp.data.api.responses.models.orders.order.OrderTradeItem

/**
 * @author Denis Makovskyi
 */

data class OrderResponse(
    @SerializedName("shop")
    val shop: BaseOrderShop,

    @SerializedName("rs_order")
    val consumerOrder: ConsumerOrder?,

    @SerializedName("order_trade_items")
    val tradeItems: List<OrderTradeItem>
): BaseOrderResponse()