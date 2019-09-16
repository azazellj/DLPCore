package ua.com.wl.dlp.data.api.responses.shop.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.order.PreOrderShop
import ua.com.wl.dlp.data.api.responses.models.shop.order.PreOrderStatus

/**
 * @author Denis Makovskyi
 */

open class BasePreOrderResponse(
    @SerializedName("number") val number: Int? = null,
    @SerializedName("status") val status: PreOrderStatus? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("price_total") val totalPrice: String? = null,
    @SerializedName("shop") val shop: PreOrderShop? = null)