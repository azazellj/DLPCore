package ua.com.wl.dlp.data.api.responses.models.shop.offer.promo

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PromoParams(
    @SerializedName("gift_name") val giftName: String?,
    @SerializedName("sale_price") val salePrice: String?,
    @SerializedName("event_price") val eventPrice: String?,
    @SerializedName("event_place") val eventPlace: String?,
    @SerializedName("discount_size") val discountSize: Long?,
    @SerializedName("discount_price") val discountPrice: String?,
    @SerializedName("promo_price_in_bonuses") val priceInBonuses: Long?,
    @SerializedName("exclusive_cash_back_size") val cashBackSize: Long?)