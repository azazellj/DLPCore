package ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PreOrderItem(
    @SerializedName("offer")
    val offer: Int,

    @SerializedName("count")
    val count: Int
) {

    class Builder {

        private var offer: Int = 0
        private var count: Int = 0

        fun offer(init: () -> Int) {
            offer = init()
        }

        fun count(init: () -> Int) {
            count = init()
        }

        fun build(init: Builder.() -> Unit): PreOrderItem {
            init()
            return PreOrderItem(offer, count)
        }
    }
}