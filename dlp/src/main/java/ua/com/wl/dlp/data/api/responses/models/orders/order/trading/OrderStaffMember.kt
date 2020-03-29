package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderStaffMember(
    @SerializedName("id")
    val id: Int,

    @SerializedName("full_name")
    val fullName: String)