package ua.com.wl.dlp.data.api.responses.shop.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.order.PreOrderShop
import ua.com.wl.dlp.data.api.responses.models.shop.order.PreOrderStatus

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