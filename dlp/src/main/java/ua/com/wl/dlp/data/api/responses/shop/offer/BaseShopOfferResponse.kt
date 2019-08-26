package ua.com.wl.dlp.data.api.responses.shop.offer

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoSettings

open class BaseShopOfferResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("trade_item") val tradeItem: Int = 0,
    @SerializedName("is_new") val isNew: Boolean = false,
    @SerializedName("in_favourite") val isFavourite: Boolean = false,
    @SerializedName("is_promo_offer") val isPromoOffer: Boolean = false,
    @SerializedName("name") val name: String = "",
    @SerializedName("thumb_image") val thumbImage: String = "",
    @SerializedName("short_description") val shortDescription: String? = null,
    @SerializedName("outcome") val outcome: String? = null,
    @SerializedName("price_in_uah") val priceInCurrency: String? = null,
    @SerializedName("price_in_bonuses") val priceInBonuses: Long? = null,
    @SerializedName("cash_back_percentage") val cashBackPercentage: Int? = null,
    @SerializedName("promo_settings") val promoSettings: PromoSettings? = null,
    @SerializedName("novelty_date_range") val noveltyDatesRange: NoveltyDatesRange? = null)