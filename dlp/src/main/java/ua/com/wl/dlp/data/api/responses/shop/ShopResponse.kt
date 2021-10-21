package ua.com.wl.dlp.data.api.responses.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.api.responses.models.shop.WorkSchedule
import ua.com.wl.dlp.data.api.responses.models.shop.chain.Chain
import ua.com.wl.dlp.data.api.responses.models.shop.chain.ShopConfiguration
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.DeliveryPrice
import ua.com.wl.dlp.data.api.responses.models.shop.delivery.PaymentOptions
import ua.com.wl.dlp.data.api.responses.models.shop.service.ShopService

@JsonClass(generateAdapter = true)
data class ShopResponse(
    // BaseShopResponse fields
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "thumb_logo")
    var thumbLogo: String? = null,

    @Json(name = "thumb_photo")
    var thumbPhoto: String? = null,

    @Json(name = "thumb_photos")
    var thumbPhotos: List<String>? = null,

    @Json(name = "phone")
    val phone: String? = null,

    @Json(name = "address")
    var address: String = "",

    @Json(name = "schedule")
    var schedule: String = "",

    @Json(name = "coordinates_str")
    var coordinates: String = "",

    @Json(name = "is_pending")
    var isPending: Boolean = false,

    @Json(name = "cash_back_percentage")
    var cashBackPercentage: Int? = null,

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

    @Json(name = "chain")
    var chain: Chain? = null,

    @Json(name = "configuration")
    var configuration: ShopConfiguration? = null,

    @Json(name = "city")
    var city: City? = null,

    @Json(name = "services")
    var services: List<ShopService>? = null,

    // ShopResponse fields
    @Json(name = "description")
    val description: String? = null,

    @Json(name = "in_favorite")
    val isFavourite: Boolean? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "fb_link")
    val facebookLink: String? = null,

    @Json(name = "insta_link")
    val instagramLink: String? = null,

    @Json(name = "delivery_minimal_sum")
    val deliveryMinAmount: String? = null,

    @Json(name = "delivery_payment")
    val deliveryPaymentOptions: PaymentOptions? = null,

    @Json(name = "delivery_prices")
    val deliveryProbablyPrices: List<DeliveryPrice>? = emptyList(),
)