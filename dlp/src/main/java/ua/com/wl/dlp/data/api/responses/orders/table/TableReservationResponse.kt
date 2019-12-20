package ua.com.wl.dlp.data.api.responses.orders.table

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.requests.orders.table.TableReservationRequest
import ua.com.wl.dlp.data.api.responses.models.shop.table.TableReservationShop
import ua.com.wl.dlp.data.api.responses.models.shop.table.TableReservationStatus

/**
 * @author Denis Makovskyi
 */

data class TableReservationResponse constructor(
    @SerializedName("id")
    val id: Int,

    @SerializedName("shop")
    val shop: TableReservationShop,

    @SerializedName("status")
    val status: TableReservationStatus
) : TableReservationRequest()