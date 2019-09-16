package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.service.ShopService

/**
 * @author Denis Makovskyi
 */

open class BaseShopResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("thumb_logo") var thumbLogo: String? = null,
    @SerializedName("thumb_photo") var thumbPhoto: String? = null,
    @SerializedName("thumb_photos") var thumbPhotos: List<String>? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("schedule") var schedule: String? = null,
    @SerializedName("coordinates_str") var coordinates: String? = null,
    @SerializedName("is_pending") var isPending: Boolean? = null,
    @SerializedName("cash_back_percentage") var cashBackPercentage: Int? = null,
    @SerializedName("allow_pre_order") var isPreOrdersAllowed: Boolean? = null,
    @SerializedName("allow_table_reservation") var isTableReservationAllowed: Boolean? = null,
    @SerializedName("services") var services: List<ShopService>? = null)