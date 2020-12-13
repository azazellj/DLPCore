package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class OrderStaffMember(
    @Json(name = "id")
    val id: Int,

    @Json(name = "full_name")
    val fullName: String
)