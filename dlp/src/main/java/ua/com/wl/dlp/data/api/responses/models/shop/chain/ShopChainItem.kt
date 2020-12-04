package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.auth.City

class ShopChainItem(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("is_pending")
    var isPending: Boolean = false,

    @SerializedName("thumb_logo")
    var thumbLogoUrl: String? = null,

    @SerializedName("allow_pre_order_delivery")
    var isPreOrderDeliveryAllowed: Boolean = false,

    @SerializedName("allow_pre_order_takeaway")
    var isPreOrderTakeawayAllowed: Boolean = false,

    @SerializedName("allow_pre_order_in_place")
    var isPreOrderInPlaceAllowed: Boolean = false,

    @SerializedName("address")
    var address: String? = null,

    @SerializedName("city")
    var city: City? = null)