package ua.com.wl.dlp.data.api.responses.models.shop.offer.promo

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

@Deprecated(
    message = "This class will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
data class PromoParams(
    @SerializedName("gift_name")
    val giftName: String? = null,

    @SerializedName("sale_price")
    val salePrice: String? = null,

    @SerializedName("event_price")
    val eventPrice: String? = null,

    @SerializedName("event_place")
    val eventPlace: String? = null,

    @SerializedName("discount_size")
    val discountSize: Long? = null,

    @SerializedName("discount_price")
    val discountPrice: String? = null,

    @SerializedName("promo_price_in_bonuses")
    val priceInBonuses: Long? = null,

    @SerializedName("exclusive_cash_back_size")
    val cashBackSize: Long? = null)