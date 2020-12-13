package ua.com.wl.dlp.data.api.responses.models.consumer.rank

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankPermissions(
    @Json(name = "cash_back_percentage")
    val cashBackPercentage: Int,
    @Json(name = "allow_offer_view")
    val isOfferViewAllowed: Boolean,
    @Json(name = "allow_offer_sharing")
    val isOfferSharingAllowed: Boolean,
    @Json(name = "allow_news_item_sharing")
    val isArticleSharingAllowed: Boolean,
    @Json(name = "allow_pre_order")
    val isPreOrderAllowed: Boolean,
    @Json(name = "allow_table_reservation")
    val isTableReservationAllowed: Boolean
)