package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.PreOrderItem

/**
 * @author Denis Makovskyi
 */

data class PreOrderRequest(
    @SerializedName("shop")
    val shopId: Int,

    @SerializedName("readiness_date")
    val readinessDate: String,

    @SerializedName("readiness_time")
    val readinessTime: String,

    @SerializedName("pay_with_bonuses")
    val useBonuses: Boolean?,

    @SerializedName("count_of_bonuses")
    val bonusesCount: Long?,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("info")
    val info: BasePreOrderInfo?,

    @SerializedName("receipt")
    val receipt: List<PreOrderItem>
) {
    
    class Builder {

        private var shopId: Int = 0
        private var readinessDate: String = ""
        private var readinessTime: String = ""
        private var useBonuses: Boolean = false
        private var bonusesCount: Long = 0L
        private var comment: String? = null
        private var info: BasePreOrderInfo? = null
        private val receipt: MutableList<PreOrderItem> = mutableListOf()

        fun shopId(init: () -> Int) {
            shopId = init()
        }

        fun readinessDate(init: () -> String) {
            readinessDate = init()
        }

        fun readinessTime(init: () -> String) {
            readinessTime = init()
        }

        fun useBonuses(init: () -> Boolean) {
            useBonuses = init()
        }

        fun bonusesCount(init: () -> Long) {
            bonusesCount = init()
        }

        fun comment(init: () -> String?) {
            comment = init()
        }

        fun info(init: BasePreOrderInfo.Builder.() -> Unit) {
            info = BasePreOrderInfo.Builder().build(init)
        }

        fun receiptItem(init: PreOrderItem.Builder.() -> Unit) {
            receipt.add(PreOrderItem.Builder().build(init))
        }

        fun build(init: Builder.() -> Unit): PreOrderRequest {
            init()
            return PreOrderRequest(
                shopId, readinessDate, readinessTime,
                useBonuses, bonusesCount, comment, info, receipt)
        }
    }
}

fun preOrderRequest(init: PreOrderRequest.Builder.() -> Unit): PreOrderRequest {
    return PreOrderRequest.Builder().build(init)
}