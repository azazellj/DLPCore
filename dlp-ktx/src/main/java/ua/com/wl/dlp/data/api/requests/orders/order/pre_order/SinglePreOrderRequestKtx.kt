package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.BasePreOrderInfoBuilder

class SinglePreOrderRequestBuilder : MultiPreOrderRequestBuilder() {
    private var info: BasePreOrderInfo? = null

    fun info(init: BasePreOrderInfoBuilder.() -> Unit) {
        info = BasePreOrderInfoBuilder().build(init)
    }

    fun build(init: SinglePreOrderRequestBuilder.() -> Unit): SinglePreOrderRequest {
        init()
        return SinglePreOrderRequest(
            info, shopId, readinessDate,
            readinessTime, useBonuses,
            bonusesCount, comment, receipt
        )
    }
}

fun preOrderRequest(init: SinglePreOrderRequestBuilder.() -> Unit): SinglePreOrderRequest {
    return SinglePreOrderRequestBuilder().build(init)
}