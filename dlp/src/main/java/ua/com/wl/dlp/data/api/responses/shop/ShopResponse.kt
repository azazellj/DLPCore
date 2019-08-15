package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName
import com.idanatz.oneadapter.external.interfaces.Diffable

import ua.com.wl.dlp.data.api.responses.models.shop.service.ShopService

/**
 * @author Denis Makovskyi
 */

class ShopResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("thumb_logo") val thumbLogo: String?,
    @SerializedName("thumb_photo") val thumbPhoto: String?,
    @SerializedName("thumb_photos") val thumbPhotos: List<String>?,
    @SerializedName("address") val address: String,
    @SerializedName("is_pending") val isPending: Boolean,
    @SerializedName("schedule") val schedule: String?,
    @SerializedName("coordinates_str") val coordinates: String?,
    @SerializedName("cash_back_percentage") val cashBackPercentage: Int,
    @SerializedName("allow_pre_order") val isPreOrdersAllowed: Boolean,
    @SerializedName("allow_table_reservation") val isTableReservationAllowed: Boolean,
    @SerializedName("services") val services: List<ShopService>?): Diffable {

    override fun getUniqueIdentifier(): Long = id.toLong()

    override fun areContentTheSame(other: Any): Boolean = other is ShopResponse && other.id == id
}