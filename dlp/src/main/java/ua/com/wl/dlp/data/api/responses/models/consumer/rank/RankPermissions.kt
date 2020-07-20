package ua.com.wl.dlp.data.api.responses.models.consumer.rank

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class RankPermissions(
    @SerializedName("cash_back_percentage")
    val cashBackPercentage: Int,
    @SerializedName("allow_offer_view")
    val isOfferViewAllowed: Boolean,
    @SerializedName("allow_offer_sharing")
    val isOfferSharingAllowed: Boolean,
    @SerializedName("allow_news_item_sharing")
    val isArticleSharingAllowed: Boolean,
    @SerializedName("allow_pre_order")
    val isPreOrderAllowed: Boolean,
    @SerializedName("allow_table_reservation")
    val isTableReservationAllowed: Boolean)