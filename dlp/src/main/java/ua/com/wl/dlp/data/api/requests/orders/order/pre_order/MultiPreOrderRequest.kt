package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.PreOrderItem

/**
 * @author Denis Makovskyi
 */

open class MultiPreOrderRequest(
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

    @SerializedName("receipt")
    val receipt: List<PreOrderItem>
) {

    open class Builder {

        protected var shopId: Int = 0
        protected var readinessDate: String = ""
        protected var readinessTime: String = ""
        protected var useBonuses: Boolean = false
        protected var bonusesCount: Long = 0L
        protected var comment: String? = null
        protected val receipt: MutableList<PreOrderItem> = mutableListOf()

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

        fun receiptItem(init: PreOrderItem.Builder.() -> Unit) {
            receipt.add(PreOrderItem.Builder().build(init))
        }

        fun build(init: Builder.() -> Unit): MultiPreOrderRequest {
            init()
            return MultiPreOrderRequest(
                shopId, readinessDate, readinessTime,
                useBonuses, bonusesCount, comment, receipt)
        }
    }
}