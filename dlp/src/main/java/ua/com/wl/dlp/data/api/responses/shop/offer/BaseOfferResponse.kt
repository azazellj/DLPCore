package ua.com.wl.dlp.data.api.responses.shop.offer

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.offer.item.OfferItem
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoSettings

@JsonClass(generateAdapter = true)
@Parcelize
open class BaseOfferResponse(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "is_new")
    var isNew: Boolean = false,

    @Deprecated(message = "This field will be removed in further revisions. This change caused by offer promo structure refactoring.")
    @Json(name = "is_promo_offer")
    var isPromo: Boolean? = null,

    @Json(name = "in_favorite")
    var isFavourite: Boolean = false,

    @Json(name = "hide_price")
    var isPriceHidden: Boolean = false,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "outcome")
    var outcome: String = "",

    @Json(name = "thumb_image")
    var thumbImage: String? = null,

    @Json(name = "short_description")
    var shortDescription: String = "",

    @Json(name = "item")
    var offerItem: OfferItem? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith(
            "BaseOfferResponse.offerItem",
            "ua.com.wl.dlp.data.api.responses.shop.offer"
        )
    )
    @Json(name = "trade_item")
    var tradeItem: Int? = null,

    @Transient
    var preOrdersCount: Int = 0,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith(
            "BaseOfferResponse.offerItem",
            "ua.com.wl.dlp.data.api.responses.shop.offer"
        )
    )
    @Json(name = "price_in_bonuses")
    var priceInBonuses: Long? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith(
            "BaseOfferResponse.offerItem",
            "ua.com.wl.dlp.data.api.responses.shop.offer"
        )
    )
    @Json(name = "price_in_uah")
    var priceInCurrency: String? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith(
            "BaseOfferResponse.offerItem",
            "ua.com.wl.dlp.data.api.responses.shop.offer"
        )
    )
    @Json(name = "cash_back_percentage")
    var cashBackPercentage: Int? = null,

    @Json(name = "publication_end_date")
    var publicationEndDate: String? = null,

    @Json(name = "publication_start_date")
    var publicationStartDate: String? = null,

    @Deprecated(message = "This field will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
    @Json(name = "promo_settings")
    var promoSettings: PromoSettings? = null,

    @Json(name = "novelty_date_range")
    var noveltyDatesRange: NoveltyDatesRange? = null
) : Parcelable