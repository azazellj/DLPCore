package ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order

class PreOrderItemBuilder {
    private var offer: Int = 0
    private var count: Int = 0

    fun offer(init: () -> Int) {
        offer = init()
    }

    fun count(init: () -> Int) {
        count = init()
    }

    fun build(init: PreOrderItemBuilder.() -> Unit): PreOrderItem {
        init()
        return PreOrderItem(offer, count)
    }
}

fun preOrderItem(init: PreOrderItemBuilder.() -> Unit): PreOrderItem {
    return PreOrderItemBuilder().build(init)
}