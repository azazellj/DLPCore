package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.BasePreOrderInfoBuilder

class GeneralPreOrderRequestBuilder {
    private var info: BasePreOrderInfo? = null
    private val preOrders: MutableList<MultiPreOrderRequest> = mutableListOf()

    fun info(init: BasePreOrderInfoBuilder.() -> Unit) {
        info = BasePreOrderInfoBuilder().build(init)
    }

    fun preOrder(init: MultiPreOrderRequestBuilder.() -> Unit) {
        preOrders.add(MultiPreOrderRequestBuilder().build(init))
    }

    fun build(init: GeneralPreOrderRequestBuilder.() -> Unit): GeneralPreOrderRequest {
        init()
        return GeneralPreOrderRequest(info, preOrders)
    }
}

fun generalPreOrderRequest(init: GeneralPreOrderRequestBuilder.() -> Unit): GeneralPreOrderRequest {
    return GeneralPreOrderRequestBuilder().build(init)
}