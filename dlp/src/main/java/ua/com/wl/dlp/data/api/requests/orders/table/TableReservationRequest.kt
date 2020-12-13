package ua.com.wl.dlp.data.api.requests.orders.table

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TableReservationRequest(
    @Json(name = "shop")
    val shopId: Int,

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
    val comment: String?
)