package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo

/**
 * @author Denis Makovskyi
 */

data class GeneralPreOrderRequest(
    @SerializedName("info")
    val info: BasePreOrderInfo?,

    @SerializedName("pre_orders")
    val preOrders: List<MultiPreOrderRequest>
) {

    class Builder {

        private var info: BasePreOrderInfo? = null
        private val preOrders: MutableList<MultiPreOrderRequest> = mutableListOf()

        fun info(init: BasePreOrderInfo.Builder.() -> Unit) {
            info = BasePreOrderInfo.Builder().build(init)
        }

        fun preOrder(init: MultiPreOrderRequest.Builder.() -> Unit) {
            preOrders.add(MultiPreOrderRequest.Builder().build(init))
        }

        fun build(init: Builder.() -> Unit): GeneralPreOrderRequest {
            init()
            return GeneralPreOrderRequest(info, preOrders)
        }
    }
}

fun generalPreOrderRequest(init: GeneralPreOrderRequest.Builder.() -> Unit): GeneralPreOrderRequest {
    return GeneralPreOrderRequest.Builder().build(init)
}