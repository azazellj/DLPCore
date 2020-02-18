package ua.com.wl.dlp.data.api.responses.orders.order.rate

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.orders.order.Order

/**
 * @author Denis Makovskyi
 */

open class BaseOrderRateResponse(
    @SerializedName("value")
    var value: Int = 0,

    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("order")
    var order: Order? = null)