package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderShop
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderStatus

/**
 * @author Denis Makovskyi
 */

open class BasePreOrderResponse(
    @SerializedName("number")
    var number: Int = 0,

    @SerializedName("status")
    var status: PreOrderStatus? = null,

    @SerializedName("created_at")
    var createdAt: String = "",

    @SerializedName("price_total")
    var totalPrice: String = "",

    @SerializedName("shop")
    var shop: PreOrderShop? = null)