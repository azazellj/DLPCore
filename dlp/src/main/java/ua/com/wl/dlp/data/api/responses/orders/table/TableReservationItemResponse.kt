package ua.com.wl.dlp.data.api.responses.orders.table

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.table.TableReservationItemShop
import ua.com.wl.dlp.data.api.responses.models.orders.table.TableReservationStatus

/**
 * @author Denis Makovskyi
 */

data class TableReservationItemResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("shop")
    val shopId: Int,

    @SerializedName("shop_detail")
    val shop: TableReservationItemShop,

    @SerializedName("date")
    val date: String?,

    @SerializedName("time_from")
    val timeFrom: String?,

    @SerializedName("time_to")
    val timeTo: String?,

    @SerializedName("reserve_one_table")
    val singleTable: Boolean,

    @SerializedName("guests_count")
    val guestsCount: Int,

    @SerializedName("child_guests_count")
    val childrenCount: Int,

    @SerializedName("pre_ordered_flag")
    val isPreOrdered: Boolean,

    @SerializedName("smoking_place_flag")
    val needSmokingPlace: Boolean,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("status")
    val status: TableReservationStatus)