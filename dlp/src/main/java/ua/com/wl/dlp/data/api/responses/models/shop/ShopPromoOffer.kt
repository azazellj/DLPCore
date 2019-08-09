package ua.com.wl.dlp.data.api.responses.models.shop

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.promo.PromoSettings

/**
 * @author Denis Makovskyi
 */

data class ShopPromoOffer(
    @SerializedName("id") val id: Int,
    @SerializedName("trade_item") val tradeItem: Int,
    @SerializedName("name") val name: String,
    @SerializedName("outcome") val outcome: String?,
    @SerializedName("short_description") val description: String,
    @SerializedName("price_in_bonuses") val priceInBonuses: Long,
    @SerializedName("price_in_uah") val priceInCurrency: String,
    @SerializedName("cash_back_percentage") val cashBackPercentage: String?,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("is_promo_offer") val isPromoOffer: Boolean,
    @SerializedName("promo_settings") val promoSettings: PromoSettings,
    @SerializedName("novelty_date_range") val noveltyDatesRange: NoveltyDatesRange)