package ua.com.wl.dlp.data.api.responses.models.orders.table

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TableReservationDetailedShop(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "city")
    val city: String,

    @Json(name = "address")
    val address: String
)