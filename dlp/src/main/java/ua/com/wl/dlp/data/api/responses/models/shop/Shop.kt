package ua.com.wl.dlp.data.api.responses.models.shop

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.shop.service.ShopService

/**
 * @author Denis Makovskyi
 */

data class Shop(
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
    @SerializedName("services") val services: List<ShopService>?)