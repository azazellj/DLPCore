package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.PreOrderItem
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.PreOrderItemBuilder

open class MultiPreOrderRequestBuilder {
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

    fun receiptItem(init: PreOrderItemBuilder.() -> Unit) {
        receipt.add(PreOrderItemBuilder().build(init))
    }

    fun build(init: MultiPreOrderRequestBuilder.() -> Unit): MultiPreOrderRequest {
        init()
        return MultiPreOrderRequest(
            shopId, readinessDate, readinessTime,
            useBonuses, bonusesCount, comment, receipt
        )
    }
}
