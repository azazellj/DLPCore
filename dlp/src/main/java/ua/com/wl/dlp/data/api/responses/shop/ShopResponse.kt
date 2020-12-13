package ua.com.wl.dlp.data.api.responses.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.shop.WorkSchedule
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.DeliveryPrice
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.PaymentOptions

@JsonClass(generateAdapter = true)
data class ShopResponse(
    @Json(name = "description")
    val description: String,

    @Json(name = "in_favorite")
    val isFavourite: Boolean,

    @Json(name = "link")
    val link: String,

    @Json(name = "fb_link")
    val facebookLink: String,

    @Json(name = "insta_link")
    val instagramLink: String,

    @Json(name = "delivery_minimal_sum")
    val deliveryMinAmount: String?,

    @Json(name = "delivery_payment_options")
    val deliveryPaymentOptions: PaymentOptions,

    @Json(name = "delivery_prices")
    val deliveryProbablyPrices: List<DeliveryPrice>,

    @Json(name = "pre_order_work_schedule")
    val preOrderingWorkSchedule: List<WorkSchedule>?
) : BaseShopResponse()