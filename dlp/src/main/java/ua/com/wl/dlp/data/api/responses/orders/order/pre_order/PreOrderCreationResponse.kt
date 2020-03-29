package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PreOrderInfo
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderTradeItem

/**
 * @author Denis Makovskyi
 */

data class PreOrderCreationResponse(
    @SerializedName("shop")
    val shopId: Int,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("readiness_time")
    val readinessTime: String?,

    @SerializedName("pay_with_bonuses")
    val payBonuses: Boolean,

    @SerializedName("count_of_bonuses")
    val bonusesCount: Long,

    @SerializedName("count_of_bonuses_money")
    val currencyEquivalent: String,

    @SerializedName("info")
    val info: PreOrderInfo?,

    @SerializedName("pre_order_trade_items")
    val tradeItems: List<BasePreOrderTradeItem>
)