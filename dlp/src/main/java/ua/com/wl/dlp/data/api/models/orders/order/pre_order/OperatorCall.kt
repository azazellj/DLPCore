package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.squareup.moshi.Json


/**
 * @author Denis Makovskyi
 */

enum class OperatorCall {
    @Json(name = "WAITING")
    WAITING,

    @Json(name = "NOT_NEED")
    NOT_NEED
}