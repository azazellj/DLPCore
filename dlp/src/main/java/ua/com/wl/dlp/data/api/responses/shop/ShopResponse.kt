package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.shop.WorkSchedule
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.DeliveryPrice
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.PaymentOptions

/**
 * @author Denis Makovskyi
 */

data class ShopResponse(
    @SerializedName("description")
    val description: String,

    @SerializedName("in_favorite")
    val isFavourite: Boolean,

    @SerializedName("link")
    val link: String,

    @SerializedName("fb_link")
    val facebookLink: String,

    @SerializedName("insta_link")
    val instagramLink: String,

    @SerializedName("delivery_minimal_sum")
    val deliveryMinAmount: String?,

    @SerializedName("delivery_payment_options")
    val deliveryPaymentOptions: PaymentOptions,

    @SerializedName("delivery_prices")
    val deliveryProbablyPrices: List<DeliveryPrice>,

    @SerializedName("pre_order_work_schedule")
    val preOrderingWorkSchedule: List<WorkSchedule>?
) : BaseShopResponse()