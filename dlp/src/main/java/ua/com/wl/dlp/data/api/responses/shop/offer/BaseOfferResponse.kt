package ua.com.wl.dlp.data.api.responses.shop.offer

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.offer.item.OfferItem
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoSettings

/**
 * @author Denis Makovskyi
 */

open class BaseOfferResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("is_new")
    var isNew: Boolean = false,

    @Deprecated(message = "This field will be removed in further revisions. This change caused by offer promo structure refactoring.")
    @SerializedName("is_promo_offer")
    var isPromo: Boolean? = null,

    @SerializedName("in_favorite")
    var isFavourite: Boolean = false,

    @SerializedName("hide_price")
    var isPriceHidden: Boolean = false,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("outcome")
    var outcome: String = "",

    @SerializedName("thumb_image")
    var thumbImage: String? = null,

    @SerializedName("short_description")
    var shortDescription: String = "",

    @SerializedName("item")
    var offerItem: OfferItem? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith("BaseOfferResponse.offerItem", "ua.com.wl.dlp.data.api.responses.shop.offer"))
    @SerializedName("trade_item")
    var tradeItem: Int? = null,

    @Transient
    var preOrdersCount: Int = 0,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith("BaseOfferResponse.offerItem", "ua.com.wl.dlp.data.api.responses.shop.offer"))
    @SerializedName("price_in_bonuses")
    var priceInBonuses: Long? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith("BaseOfferResponse.offerItem", "ua.com.wl.dlp.data.api.responses.shop.offer"))
    @SerializedName("price_in_uah")
    var priceInCurrency: String? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith("BaseOfferResponse.offerItem", "ua.com.wl.dlp.data.api.responses.shop.offer"))
    @SerializedName("cash_back_percentage")
    var cashBackPercentage: Int? = null,

    @SerializedName("publication_end_date")
    var publicationEndDate: String? = null,

    @SerializedName("publication_start_date")
    var publicationStartDate: String? = null,

    @Deprecated(message = "This field will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
    @SerializedName("promo_settings")
    var promoSettings: PromoSettings? = null,

    @SerializedName("novelty_date_range")
    var noveltyDatesRange: NoveltyDatesRange? = null)