package ua.com.wl.dlp.data.api.requests.orders.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class RateOrderRequest(
    @SerializedName("value")
    val value: Int,

    @SerializedName("comment")
    val comment: String
) {

    class Builder {

        private var value: Int = 0
        private var comment: String = ""

        fun value(init: () -> Int) {
            value = init()
        }

        fun comment(init: () -> String) {
            comment = init()
        }

        fun build(init: Builder.() -> Unit): RateOrderRequest {
            init()
            return RateOrderRequest(value, comment)
        }
    }
}

fun rateOrderRequest(init: RateOrderRequest.Builder.() -> Unit): RateOrderRequest {
    return RateOrderRequest.Builder().build(init)
}