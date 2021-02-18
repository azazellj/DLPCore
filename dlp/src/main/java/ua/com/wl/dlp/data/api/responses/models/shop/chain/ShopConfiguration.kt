package ua.com.wl.dlp.data.api.responses.models.shop.chain

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.PaymentOptions

/**
 * @author Oleh "MADCAT" Rysniuk
 */

data class ShopConfiguration(
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

        @SerializedName("cash_back_percentage")
        var cashBackPercentage: Int,

        @SerializedName("delivery_payment")
        val deliveryPaymentOptions: PaymentOptions,

        @SerializedName("delivery_minimal_sum")
        val deliveryMinimalSum: Double,

        @SerializedName("payment_online_available")
        var isPaymentOnlineAvailable: Boolean = false)