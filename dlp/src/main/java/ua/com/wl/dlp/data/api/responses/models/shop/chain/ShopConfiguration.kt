package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.PaymentOptions

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

    @Json(name = "delivery_payment")
    val deliveryPaymentOptions: PaymentOptions,

    @Json(name = "delivery_minimal_sum")
    val deliveryMinimalSum: Double,

    @Json(name = "payment_online_available")
    var isPaymentOnlineAvailable: Boolean = false
)