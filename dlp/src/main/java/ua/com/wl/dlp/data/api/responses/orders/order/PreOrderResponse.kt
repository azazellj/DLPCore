package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.order.PreOrderTradeItem

/**
 * @author Denis Makovskyi
 */

data class PreOrderResponse(
    @SerializedName("pay_with_bonuses")
    val usedBonuses: Boolean,

    @SerializedName("count_of_bonuses")
    val bonusesCount: Long,

    @SerializedName("readiness_date")
    val readinessDate: String?,

    @SerializedName("readiness_time")
    val readinessTime: String?,

    @SerializedName("pre_order_trade_items")
    val tradeItems: List<PreOrderTradeItem>) : BasePreOrderResponse()