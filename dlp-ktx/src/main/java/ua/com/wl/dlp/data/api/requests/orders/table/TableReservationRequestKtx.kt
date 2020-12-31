package ua.com.wl.dlp.data.api.requests.orders.table

class TableReservationRequestBuilder {
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

    fun build(init: TableReservationRequestBuilder.() -> Unit): TableReservationRequest {
        init()
        return TableReservationRequest(
            shopId, date,
            timeFrom, timeTo,
            singleTable, guestsCount,
            childrenCount, isPreOrdered,
            needSmokingPlace, comment
        )
    }
}

fun tableReservationRequest(init: TableReservationRequestBuilder.() -> Unit): TableReservationRequest {
    return TableReservationRequestBuilder().build(init)
}