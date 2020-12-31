package ua.com.wl.dlp.data.api.responses.models.shop.offer.promo

import com.squareup.moshi.Json

@Deprecated(message = "This class will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
enum class PromoType {
    @Json(name = "GIFT")
    GIFT,

    @Json(name = "SALE")
    SALE,

    @Json(name = "EVENT")
    EVENT,

    @Json(name = "DISCOUNT")
    DISCOUNT,

    @Json(name = "BY_BONUS")
    BY_BONUS,

    @Json(name = "EXCLUSIVE_CASH_BACK")
    EXCLUSIVE_CASH_BACK,
}