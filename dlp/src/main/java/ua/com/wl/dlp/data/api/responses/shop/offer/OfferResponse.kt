package ua.com.wl.dlp.data.api.responses.shop.offer

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.offer.item.OfferItem
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.Permissions
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoSettings
import ua.com.wl.dlp.data.api.responses.models.social.SocialNetwork

@JsonClass(generateAdapter = true)
@Parcelize
data class OfferResponse(
    // BaseOfferResponse fields
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
            "OfferResponse.offerItem",
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
            "OfferResponse.offerItem",
            "ua.com.wl.dlp.data.api.responses.shop.offer"
        )
    )
    @Json(name = "price_in_bonuses")
    var priceInBonuses: Long? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith(
            "OfferResponse.offerItem",
            "ua.com.wl.dlp.data.api.responses.shop.offer"
        )
    )
    @Json(name = "price_in_uah")
    var priceInCurrency: String? = null,

    @Deprecated(
        message = "This field will be moved in specific object.",
        replaceWith = ReplaceWith(
            "OfferResponse.offerItem",
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

    @Json(name = "permissions")
    var permissions: Permissions? = null,

    @Json(name = "novelty_date_range")
    var noveltyDatesRange: NoveltyDatesRange? = null,

    // OfferResponse fields

    @Json(name = "tags")
    val tags: String?,

    @Json(name = "ingredients")
    val ingredients: String?,

    @Json(name = "description")
    val fullDescription: String?,

    @Json(name = "fb_link")
    val facebookLink: String?,

    @Json(name = "payed_per_view")
    val payedPerView: Boolean?,

    @Json(name = "gives_bonuses_per_view")
    val givesBonusesPerView: Boolean?,

    @Json(name = "per_view_amount")
    val bonusesPerView: Long?,

    @Json(name = "gives_bonuses_for_sharing")
    val givesBonusesPerSharing: Boolean?,

    @Json(name = "for_sharing_amount")
    val bonusesPerSharing: Long?,

    @Json(name = "for_sharing_social_networks")
    val sharingSocialNetwork: List<SocialNetwork>? = emptyList(),

    @Json(name = "payed_for_sharing_social_networks")
    val sharedSocialNetwork: List<SocialNetwork>? = emptyList(),

    @Json(name = "pre_order_available")
    val isAvailableForPreOrder: Boolean?,

    @Json(name = "delivery_available")
    val isAvailableForDelivery: Boolean?
) : Parcelable