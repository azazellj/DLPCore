package ua.com.wl.dlp.data.api.responses.orders.order.rate

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.ConsumerOrder
import ua.com.wl.dlp.data.api.responses.orders.order.rate.BaseOrderRateResponse

/**
 * @author Denis Makovskyi
 */

data class OrderRateResponse(
    @SerializedName("rs_order")
    val consumerOrder: ConsumerOrder?
): BaseOrderRateResponse()