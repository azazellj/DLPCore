package ua.com.wl.dlp.data.api.responses.news

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

import ua.com.wl.dlp.data.api.responses.models.social.SocialNetwork

@JsonClass(generateAdapter = true)
@Parcelize
data class ArticleResponse(
    // BaseArticleResponse fields

    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "thumb_image")
    var thumbImage: String? = null,

    @Json(name = "short_description")
    var shortDescription: String = "",

    @Json(name = "publication_start_datetime")
    var publicationStartDate: String = "",

    @Json(name = "publication_end_date")
    var publicationEndDate: String = "",

    // ArticleResponse fields

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "is_promo_news_item")
    val isPromo: Boolean? = null,

    @Json(name = "in_favorite")
    val isFavourite: Boolean? = null,

    @Json(name = "payed_per_view")
    val payedPerView: Boolean? = null,

    @Json(name = "gives_bonuses_per_view")
    val givesBonusesPerView: Boolean? = null,

    @Json(name = "per_view_amount")
    val bonusesPerView: Long? = null,

    @Json(name = "gives_bonuses_for_sharing")
    val givesBonusesPerSharing: Boolean? = null,

    @Json(name = "for_sharing_amount")
    val bonusesPerSharing: Long? = null,

    @Json(name = "for_sharing_social_networks")
    val sharingSocialNetwork: List<SocialNetwork>? = emptyList(),

    @Json(name = "payed_for_sharing_social_networks")
    val sharedSocialNetwork: List<SocialNetwork>? = emptyList()
) : Parcelable