package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.order.ConsumerOrder

/**
 * @author Denis Makovskyi
 */

data class OrderSimpleResponse(
    @SerializedName("shop_address")
    val shopAddress: String,

    @SerializedName("shop_thumb_logo")
    val shopThumbLogo: String,

    @SerializedName("rs_order")
    val consumerOrder: ConsumerOrder?
) : BaseOrderResponse()