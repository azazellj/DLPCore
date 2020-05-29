package ua.com.wl.dlp.data.api.requests.orders.table

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class TableReservationRequest(
    @SerializedName("shop")
    val shopId: Int,

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
    val comment: String?
) {

    class Builder {

        private var shopId: Int = 0
        private var date: String? = null
        private var timeFrom: String? = null
        private var timeTo: String? = null
        private var singleTable: Boolean = true
        private var guestsCount: Int = 1
        private var childrenCount: Int = 0
        private var isPreOrdered: Boolean = false
        private var needSmokingPlace: Boolean = false
        private var comment: String? = null

        fun shopId(init: () -> Int) {
            shopId = init()
        }

        fun date(init: () -> String?) {
            date = init()
        }

        fun timeFrom(init: () -> String?) {
            timeFrom = init()
        }

        fun timeTo(init: () -> String?) {
            timeTo = init()
        }

        fun singleTable(init: () -> Boolean) {
            singleTable = init()
        }

        fun guestsCount(init: () -> Int) {
            guestsCount = init()
        }

        fun childrenCount(init: () -> Int) {
            childrenCount = init()
        }

        fun isPreOrdered(init: () -> Boolean) {
            isPreOrdered = init()
        }

        fun needSmokingPlace(init: () -> Boolean) {
            needSmokingPlace = init()
        }

        fun comment(init: () -> String?) {
            comment = init()
        }

        fun build(init: Builder.() -> Unit): TableReservationRequest {
            init()
            return TableReservationRequest(
                shopId, date,
                timeFrom, timeTo,
                singleTable, guestsCount,
                childrenCount, isPreOrdered,
                needSmokingPlace, comment)
        }
    }
}

fun tableReservationRequest(init: TableReservationRequest.Builder.() -> Unit): TableReservationRequest {
    return TableReservationRequest.Builder().build(init)
}