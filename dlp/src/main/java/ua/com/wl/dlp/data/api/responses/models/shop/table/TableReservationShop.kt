package ua.com.wl.dlp.data.api.responses.models.shop.table

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class TableReservationShop(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("address") val address: String? = null)