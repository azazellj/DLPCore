package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

class GeneralPreOrderResponse(
    @SerializedName("pre_orders")
    val preOrders: List<PreOrderResponse>)