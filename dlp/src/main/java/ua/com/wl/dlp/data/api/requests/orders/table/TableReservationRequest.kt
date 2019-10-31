package ua.com.wl.dlp.data.api.requests.orders.table

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class TableReservationRequest(
    @SerializedName("shop")
    var shopId: Int = 0,

    @SerializedName("date")
    var date: String? = null,

    @SerializedName("time_from")
    var timeFrom: String? = null,

    @SerializedName("time_to")
    var timeTo: String? = null,

    @SerializedName("reserve_one_table")
    var singleTable: Boolean = true,

    @SerializedName("guests_count")
    var guestsCount: Int = 1,

    @SerializedName("child_guests_count")
    var childrenCount: Int = 0,

    @SerializedName("pre_ordered_flag")
    var preOrdered: Boolean = false,

    @SerializedName("smoking_place_flag")
    var needSmokingPlace: Boolean = false,

    @SerializedName("comment")
    var comment: String? = null)

fun tableReservation(
    init: TableReservationRequest.() -> Unit
): TableReservationRequest = TableReservationRequest().apply(init)