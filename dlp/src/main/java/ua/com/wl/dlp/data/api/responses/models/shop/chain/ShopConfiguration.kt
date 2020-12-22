package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Oleh "MADCAT" Rysniuk
 */
@JsonClass(generateAdapter = true)
data class ShopConfiguration(
    @Json(name = "allow_pre_orders")
    var isPreOrdersAllowed: Boolean = false,

    @Json(name = "allow_pre_order_delivery")
    var isPreOrderDeliveryAllowed: Boolean = false,

    @Json(name = "allow_pre_order_takeaway")
    var isPreOrderTakeawayAllowed: Boolean = false,

    @Json(name = "allow_pre_order_in_place")
    var isPreOrderInPlaceAllowed: Boolean = false,

    @Json(name = "allow_table_reservation")
    var isTableReservationAllowed: Boolean = false,

    @Json(name = "cash_back_percentage")
    var cashBackPercentage: Int,

    @Json(name = "payment_online_available")
    var isPaymentOnlineAvailable: Boolean = false
)