package ua.com.wl.dlp.data.api.requests.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.order.*

/**
 * @author Denis Makovskyi
 */

data class PreOrderRequest(
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

    @SerializedName("info")
    val info: PreOrderInfo?,

    @SerializedName("pre_order_trade_items")
    val tradeItems: List<BasePreOrderTradeItem>
) {
    
    class Builder {

        private var shopId: Int = 0
        private var comment: String? = null
        private var readinessTime: String? = null
        private var payBonuses: Boolean = false
        private var bonusesCount: Long = 0L
        private var info: PreOrderInfo? = null
        private var tradeItems: MutableList<BasePreOrderTradeItem> = mutableListOf()

        fun shopId(init: () -> Int) {
            shopId = init()
        }

        fun comment(init: () -> String?) {
            comment = init() ?: return
        }

        fun readinessTime(init: () -> String?) {
            readinessTime = init() ?: return
        }

        fun payBonuses(init: () -> Boolean) {
            payBonuses = init()
        }

        fun bonusesCount(init: () -> Long) {
            bonusesCount = init()
        }

        fun info(init: PreOrderInfo.Builder.() -> Unit) {
            info = PreOrderInfo.Builder().build(init)
        }

        fun tradeItem(init: BasePreOrderTradeItem.() -> Unit) {
            val tradeItem = BasePreOrderTradeItem().apply(init)
            tradeItems.add(tradeItem)
        }

        fun build(init: Builder.() -> Unit): PreOrderRequest {
            init()
            return PreOrderRequest(
                shopId, comment, readinessTime,
                payBonuses, bonusesCount, info, tradeItems)
        }
    }
}

fun preOrderRequest(init: PreOrderRequest.Builder.() -> Unit): PreOrderRequest {
    return PreOrderRequest.Builder().build(init)
}