package ua.com.wl.dlp.data.api.requests.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.GeneralPreOrderItem

/**
 * @author Denis Makovskyi
 */

class GeneralPreOrderCreationRequest constructor(
    @SerializedName("items")
    var items: List<GeneralPreOrderItem> = listOf()
) {

    fun items(init: (MutableList<GeneralPreOrderItem>) -> Unit) {
        items = mutableListOf<GeneralPreOrderItem>().apply(init)
    }
}

fun generalPreOrder(
    init: GeneralPreOrderCreationRequest.() -> Unit
): GeneralPreOrderCreationRequest = GeneralPreOrderCreationRequest().apply(init)