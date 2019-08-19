package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoSettings
import ua.com.wl.dlp.data.api.responses.models.shop.offer.social.SocialNetwork

/**
 * @author Denis Makovskyi
 */

data class ShopOfferResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("trade_item") val tradeItem: Int,
    @SerializedName("name") val name: String,
    @SerializedName("thumb_image") val thumbImage: String,
    @SerializedName("description") val fullDescription: String?,
    @SerializedName("short_description") val shortDescription: String?,
    @SerializedName("tags") val tags: String?,
    @SerializedName("fb_link") val facebookLink: String?,
    @SerializedName("outcome") val outcome: String?,
    @SerializedName("ingredients") val ingredients: String?,
    @SerializedName("price_in_bonuses") val priceInBonuses: Long?,
    @SerializedName("price_in_uah") val priceInCurrency: String?,
    @SerializedName("cash_back_percentage") val cashBackPercentage: Int?,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("in_favourite") val isFavourite: Boolean,
    @SerializedName("is_promo_offer") val isPromoOffer: Boolean,
    @SerializedName("per_view_amount") val bonusesPerView: Long?,
    @SerializedName("payed_per_view") val payedPerView: Boolean,
    @SerializedName("gives_bonuses_per_view") val givesBonusesPerView: Boolean,
    @SerializedName("for_sharing_amount") val bonusesPerSharing: Long?,
    @SerializedName("gives_bonuses_per_sharing") val givesBonusesPerSharing: Boolean,
    @SerializedName("for_sharing_social_networks") val sharingSocialNetwork: List<SocialNetwork>,
    @SerializedName("payed_for_sharing_social_networks") val sharedSocialNetwork: List<SocialNetwork>,
    @SerializedName("promo_settings") val promoSettings: PromoSettings?,
    @SerializedName("novelty_date_range") val noveltyDatesRange: NoveltyDatesRange?)