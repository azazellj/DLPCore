package ua.com.wl.dlp.data.api.responses.models.orders.table

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TableReservationItemShop(
    @Json(name = "name")
    val name: String,

    @Json(name = "address")
    val address: String?,

    @Json(name = "thumb_logo")
    val thumbImage: String
)