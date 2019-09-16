package ua.com.wl.dlp.data.api.requests.shop.table

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class TableReservationRequest(
    @SerializedName("shop") var shopId: Int? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("time_from") var timeFrom: String? = null,
    @SerializedName("time_to") var timeTo: String? = null,
    @SerializedName("reserve_one_table") var singleTable: Boolean? = null,
    @SerializedName("guests_count") var guestsCount: Int? = null,
    @SerializedName("child_guests_count") var childrenCount: Int? = null,
    @SerializedName("pre_ordered_flag") var preOrdered: Boolean? = null,
    @SerializedName("smoking_place_flag") var needSmokingPlace: Boolean? = null,
    @SerializedName("comment") var comment: String? = null)

fun tableReservation(init: TableReservationRequest.() -> Unit): TableReservationRequest = TableReservationRequest().apply(init)