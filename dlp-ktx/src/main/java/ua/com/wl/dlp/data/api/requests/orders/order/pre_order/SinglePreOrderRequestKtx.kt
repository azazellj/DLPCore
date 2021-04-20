package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderInfo
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.BasePreOrderInfoBuilder

class SinglePreOrderRequestBuilder : MultiPreOrderRequestBuilder() {
    private var info: PreOrderInfo? = null

    fun info(init: BasePreOrderInfoBuilder.() -> Unit) {
        info = BasePreOrderInfoBuilder().build(init)
    }

    fun buildSingle(init: SinglePreOrderRequestBuilder.() -> Unit): SinglePreOrderRequest {
        init()
        return SinglePreOrderRequest(
            info = info, shopId = shopId, readinessDate = readinessDate,
            readinessTime = readinessTime, useBonuses = useBonuses,
            bonusesCount = bonusesCount, comment = comment, receipt = receipt
        )
    }
}

fun preOrderRequest(init: SinglePreOrderRequestBuilder.() -> Unit): SinglePreOrderRequest {
    return SinglePreOrderRequestBuilder().buildSingle(init)
}