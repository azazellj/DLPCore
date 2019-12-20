package ua.com.wl.dlp.data.api.responses.shop.offer

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.social.SocialNetwork

/**
 * @author Denis Makovskyi
 */

data class OfferResponse constructor(
    @SerializedName("tags")
    val tags: String,

    @SerializedName("ingredients")
    val ingredients: String,

    @SerializedName("description")
    val fullDescription: String,

    @SerializedName("fb_link")
    val facebookLink: String,

    @SerializedName("payed_per_view")
    val payedPerView: Boolean,

    @SerializedName("gives_bonuses_per_view")
    val givesBonusesPerView: Boolean,

    @SerializedName("per_view_amount")
    val bonusesPerView: Long?,

    @SerializedName("gives_bonuses_for_sharing")
    val givesBonusesPerSharing: Boolean,

    @SerializedName("for_sharing_amount")
    val bonusesPerSharing: Long?,

    @SerializedName("for_sharing_social_networks")
    val sharingSocialNetwork: List<SocialNetwork>,

    @SerializedName("payed_for_sharing_social_networks")
    val sharedSocialNetwork: List<SocialNetwork>
) : BaseOfferResponse()