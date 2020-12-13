package ua.com.wl.dlp.data.api.requests.orders.order.rate

class RateOrderRequestBuilder {
    private var value: Int = 0
    private var comment: String = ""

    fun value(init: () -> Int) {
        value = init()
    }

    fun comment(init: () -> String) {
        comment = init()
    }

    fun build(init: RateOrderRequestBuilder.() -> Unit): RateOrderRequest {
        init()
        return RateOrderRequest(value, comment)
    }
}

fun rateOrderRequest(init: RateOrderRequestBuilder.() -> Unit): RateOrderRequest {
    return RateOrderRequestBuilder().build(init)
}