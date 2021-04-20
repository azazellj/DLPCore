package ua.com.wl.dlp.data.api.responses.news

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

import ua.com.wl.dlp.data.api.responses.models.social.SocialNetwork

@JsonClass(generateAdapter = true)
@Parcelize
data class ArticleResponse(
    @Json(name = "description")
    val description: String,

    @Json(name = "is_promo_news_item")
    val isPromo: Boolean,

    @Json(name = "in_favorite")
    val isFavourite: Boolean? = null,

    @Json(name = "payed_per_view")
    val payedPerView: Boolean,

    @Json(name = "gives_bonuses_per_view")
    val givesBonusesPerView: Boolean,

    @Json(name = "per_view_amount")
    val bonusesPerView: Long?,

    @Json(name = "gives_bonuses_for_sharing")
    val givesBonusesPerSharing: Boolean,

    @Json(name = "for_sharing_amount")
    val bonusesPerSharing: Long?,

    @Json(name = "for_sharing_social_networks")
    val sharingSocialNetwork: List<SocialNetwork>,

    @Json(name = "payed_for_sharing_social_networks")
    val sharedSocialNetwork: List<SocialNetwork>
) : BaseArticleResponse()