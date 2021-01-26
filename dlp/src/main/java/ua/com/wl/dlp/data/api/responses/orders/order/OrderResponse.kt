package ua.com.wl.dlp.data.api.responses.orders.order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.OrderReceipt
import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.OrderStaffMember

@JsonClass(generateAdapter = true)
@Parcelize
data class OrderResponse(
    @Json(name = "comment")
    val comment: String,

    @Json(name = "receipt")
    val receipt: OrderReceipt,

    @Json(name = "staff_member")
    val staffMember: OrderStaffMember?
) : BaseOrderResponse()