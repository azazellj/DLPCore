package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.models.order.PreOrderInfo

import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderTradeItem
import ua.com.wl.dlp.data.api.responses.orders.order.pre_order.BasePreOrderResponse

/**
 * @author Denis Makovskyi
 */

data class PreOrderResponse(
    @SerializedName("pay_with_bonuses")
    val payedBonuses: Boolean,

    @SerializedName("count_of_bonuses")
    val bonusesCount: Long,

    @SerializedName("count_of_bonuses_money")
    val currencyEquivalent: String,

    @SerializedName("readiness_date")
    val readinessDate: String?,

    @SerializedName("readiness_time")
    val readinessTime: String?,

    @SerializedName("info")
    val info: PreOrderInfo?,

    @SerializedName("pre_order_trade_items")
    val tradeItems: List<PreOrderTradeItem>
) : BasePreOrderResponse()