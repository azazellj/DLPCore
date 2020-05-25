package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.OrderReceipt
import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.OrderStaffMember

/**
 * @author Denis Makovskyi
 */

data class OrderResponse(
    @SerializedName("comment")
    val comment: String,

    @SerializedName("receipt")
    val receipt: OrderReceipt,

    @SerializedName("staff_member")
    val staffMember: OrderStaffMember?
): BaseOrderResponse()