package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.order.BaseOrderShop
import ua.com.wl.dlp.data.api.responses.models.shop.order.ConsumerOrder
import ua.com.wl.dlp.data.api.responses.models.shop.order.OrderTradeItem

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