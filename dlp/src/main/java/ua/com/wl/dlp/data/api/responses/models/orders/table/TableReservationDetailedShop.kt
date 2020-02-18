package ua.com.wl.dlp.data.api.responses.models.orders.table

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class TableReservationDetailedShop(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("address")
    val address: String)