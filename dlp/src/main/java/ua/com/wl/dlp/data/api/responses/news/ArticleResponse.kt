package ua.com.wl.dlp.data.api.responses.news

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.social.SocialNetwork

/**
 * @author Denis Makovskyi
 */

data class ArticleResponse(
    @SerializedName("description") val description: String,
    @SerializedName("is_promo_news_item") val isPromo: Boolean,
    @SerializedName("in_favorite") val isFavourite: Boolean,
    @SerializedName("payed_per_view") val payedPerView: Boolean,
    @SerializedName("gives_bonuses_per_view") val givesBonusesPerView: Boolean,
    @SerializedName("per_view_amount") val bonusesPerView: Long?,
    @SerializedName("gives_bonuses_for_sharing") val givesBonusesPerSharing: Boolean,
    @SerializedName("for_sharing_amount") val bonusesPerSharing: Long?,
    @SerializedName("for_sharing_social_networks") val sharingSocialNetwork: List<SocialNetwork>,
    @SerializedName("payed_for_sharing_social_networks") val sharedSocialNetwork: List<SocialNetwork>): BaseArticleResponse()