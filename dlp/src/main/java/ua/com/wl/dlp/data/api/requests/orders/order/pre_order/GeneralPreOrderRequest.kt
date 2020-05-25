package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class GeneralPreOrderRequest(
    @SerializedName("pre_orders")
    val preOrders: List<PreOrderRequest>
) {

    class Builder {

        private val preOrders: MutableList<PreOrderRequest> = mutableListOf()

        fun preOrder(init: PreOrderRequest.Builder.() -> Unit) {
            preOrders.add(PreOrderRequest.Builder().build(init))
        }

        fun build(init: Builder.() -> Unit): GeneralPreOrderRequest {
            init()
            return GeneralPreOrderRequest(preOrders)
        }
    }
}

fun generalPreOrderRequest(init: GeneralPreOrderRequest.Builder.() -> Unit): GeneralPreOrderRequest {
    return GeneralPreOrderRequest.Builder().build(init)
}