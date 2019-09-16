package ua.com.wl.dlp.data.api.responses.models.shop.table

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class TableReservationStatus(val value: String) {
    @SerializedName("OPENED")
    OPENED("OPENED"),
    @SerializedName("CONFIRMED")
    CONFIRMED("CONFIRMED"),
    @SerializedName("REJECTED")
    REJECTED("REJECTED"),
    @SerializedName("REJECTED_BY_CONSUMER")
    REJECTED_BY_CONSUMER("REJECTED_BY_CONSUMER")
}