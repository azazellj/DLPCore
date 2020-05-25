package ua.com.wl.dlp.data.api.responses.models.orders.table

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class TableReservationItemShop(
    @SerializedName("name")
    val name: String,

    @SerializedName("thumb_logo")
    val thumbImage: String)