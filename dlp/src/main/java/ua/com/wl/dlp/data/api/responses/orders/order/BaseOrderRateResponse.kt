package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.shop.order.Order

/**
 * @author Denis Makovskyi
 */

open class BaseOrderRateResponse constructor(
    @SerializedName("value")
    var value: Int = 0,

    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("order")
    var order: Order? = null)