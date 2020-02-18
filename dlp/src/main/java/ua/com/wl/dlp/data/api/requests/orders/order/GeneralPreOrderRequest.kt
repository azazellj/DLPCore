package ua.com.wl.dlp.data.api.requests.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.GeneralPreOrderItem

/**
 * @author Denis Makovskyi
 */

data class GeneralPreOrderRequest(
    @SerializedName("items")
    val items: List<GeneralPreOrderItem>
) {

    class Builder {

        private var items: MutableList<GeneralPreOrderItem> = mutableListOf()

        fun item(init: GeneralPreOrderItem.Builder.() -> Unit) {
            val item = GeneralPreOrderItem.Builder().build(init)
            items.add(item)
        }

        fun build(init: Builder.() -> Unit): GeneralPreOrderRequest {
            init()
            return GeneralPreOrderRequest(items)
        }
    }
}

fun generalPreOrderRequest(init: GeneralPreOrderRequest.Builder.() -> Unit): GeneralPreOrderRequest {
    return GeneralPreOrderRequest.Builder().build(init)
}