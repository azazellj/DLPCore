package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class OperatorCall(val value: String) {
    @SerializedName("WAITING")
    WAITING("WAITING"),
    @SerializedName("NOT_NEED")
    NOT_NEED("NOT_NEED")
}