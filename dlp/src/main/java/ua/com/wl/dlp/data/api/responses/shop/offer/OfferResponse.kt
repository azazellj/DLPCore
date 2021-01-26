package ua.com.wl.dlp.data.api.responses.shop.offer

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ua.com.wl.dlp.data.api.responses.models.social.SocialNetwork

@JsonClass(generateAdapter = true)
@Parcelize
data class OfferResponse(
    @Json(name = "tags")
    val tags: String,

    @Json(name = "ingredients")
    val ingredients: String,

    @Json(name = "description")
    val fullDescription: String,

    @Json(name = "fb_link")
    val facebookLink: String,

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
) : BaseOfferResponse()