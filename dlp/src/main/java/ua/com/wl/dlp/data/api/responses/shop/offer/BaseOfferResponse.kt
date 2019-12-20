package ua.com.wl.dlp.data.api.responses.shop.offer

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoSettings

/**
 * @author Denis Makovskyi
 */

open class BaseOfferResponse constructor(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("trade_item")
    var tradeItem: Int = 0,

    @SerializedName("is_new")
    var isNew: Boolean = false,

    @SerializedName("is_promo_offer")
    var isPromo: Boolean = false,

    @SerializedName("in_favorite")
    var isFavourite: Boolean = false,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("thumb_image")
    var thumbImage: String? = null,

    @SerializedName("short_description")
    var shortDescription: String = "",

    @SerializedName("outcome")
    var outcome: String = "",

    @SerializedName("price_in_uah")
    var priceInCurrency: String? = null,

    @SerializedName("price_in_bonuses")
    var priceInBonuses: Long? = null,

    @SerializedName("cash_back_percentage")
    var cashBackPercentage: Int? = null,

    @SerializedName("promo_settings")
    var promoSettings: PromoSettings? = null,

    @SerializedName("novelty_date_range")
    var noveltyDatesRange: NoveltyDatesRange? = null)