package ua.com.wl.dlp.data.api.responses.orders.table

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.orders.table.TableReservationItemShop
import ua.com.wl.dlp.data.api.responses.models.orders.table.TableReservationStatus

@JsonClass(generateAdapter = true)
data class TableReservationItemResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "shop")
    val shopId: Int,

    @Json(name = "shop_detail")
    val shop: TableReservationItemShop,

    @Json(name = "date")
    val date: String?,

    @Json(name = "time_from")
    val timeFrom: String?,

    @Json(name = "time_to")
    val timeTo: String?,

    @Json(name = "reserve_one_table")
    val singleTable: Boolean,

    @Json(name = "guests_count")
    val guestsCount: Int,

    @Json(name = "child_guests_count")
    val childrenCount: Int,

    @Json(name = "pre_ordered_flag")
    val isPreOrdered: Boolean,

    @Json(name = "smoking_place_flag")
    val needSmokingPlace: Boolean,

    @Json(name = "comment")
    val comment: String?,

    @Json(name = "status")
    val status: TableReservationStatus
)