package ua.com.wl.dlp.data.api.responses.models.shop.offer.promo

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Deprecated(message = "This class will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
@JsonClass(generateAdapter = true)
@Parcelize
data class PromoParams(
    @Json(name = "gift_name")
    val giftName: String? = null,

    @Json(name = "sale_price")
    val salePrice: String? = null,

    @Json(name = "event_price")
    val eventPrice: String? = null,

    @Json(name = "event_place")
    val eventPlace: String? = null,

    @Json(name = "discount_size")
    val discountSize: Long? = null,

    @Json(name = "discount_price")
    val discountPrice: String? = null,

    @Json(name = "promo_price_in_bonuses")
    val priceInBonuses: Long? = null,

    @Json(name = "exclusive_cash_back_size")
    val cashBackSize: Long? = null
) : Parcelable