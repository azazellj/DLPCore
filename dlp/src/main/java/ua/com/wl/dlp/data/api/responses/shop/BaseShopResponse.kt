package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.auth.City

import ua.com.wl.dlp.data.api.responses.models.shop.chain.Chain
import ua.com.wl.dlp.data.api.responses.models.shop.chain.ShopConfiguration
import ua.com.wl.dlp.data.api.responses.models.shop.service.ShopService

/**
 * @author Denis Makovskyi
 */

open class BaseShopResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("thumb_logo")
    var thumbLogo: String? = null,

    @SerializedName("thumb_photo")
    var thumbPhoto: String? = null,

    @SerializedName("thumb_photos")
    var thumbPhotos: List<String>? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("address")
    var address: String = "",

    @SerializedName("schedule")
    var schedule: String = "",

    @SerializedName("coordinates_str")
    var coordinates: String = "",

    @SerializedName("is_pending")
    var isPending: Boolean = false,

    @SerializedName("cash_back_percentage")
    var cashBackPercentage: Int? = null,

    @SerializedName("allow_pre_orders")
    var isPreOrdersAllowed: Boolean = false,

    @SerializedName("allow_pre_order_delivery")
    var isPreOrderDeliveryAllowed: Boolean = false,

    @SerializedName("allow_pre_order_takeaway")
    var isPreOrderTakeawayAllowed: Boolean = false,

    @SerializedName("allow_pre_order_in_place")
    var isPreOrderInPlaceAllowed: Boolean = false,

    @SerializedName("allow_table_reservation")
    var isTableReservationAllowed: Boolean = false,

    @SerializedName("chain")
    var chain: Chain? = null,

    @SerializedName("configuration")
    var configuration: ShopConfiguration? = null,

    @SerializedName("City")
    var city: City? = null,

    @SerializedName("services")
    var services: List<ShopService>? = null)

