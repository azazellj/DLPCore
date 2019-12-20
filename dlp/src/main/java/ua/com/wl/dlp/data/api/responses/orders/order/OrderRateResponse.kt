package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.order.ConsumerOrder

/**
 * @author Denis Makovskyi
 */

data class OrderRateResponse constructor(
    @SerializedName("rs_order")
    val consumerOrder: ConsumerOrder?
): BaseOrderRateResponse()