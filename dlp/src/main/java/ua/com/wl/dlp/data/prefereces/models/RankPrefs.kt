package ua.com.wl.dlp.data.prefereces.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.models.consumer.ranks.RankSelectionCriteriaValue

@JsonClass(generateAdapter = true)
data class RankPrefs(
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "icon_url")
    val iconUrl: String? = null,

    @Json(name = "color_hex")
    val colorHex: String? = null,

    @Json(name = "next_rank_criteria")
    val nextRankCriteria: RankCriteriaPrefs? = null,

    @Json(name = "current_rank_permissions")
    val currRankPermissions: RankPermissionsPrefs? = null
)

@JsonClass(generateAdapter = true)
data class RankCriteriaPrefs(
    @Json(name = "referral_count")
    val referralCount: RankSelectionCriteriaValue<Int, Int>? = null,

    @Json(name = "days_registered")
    val daysRegistered: RankSelectionCriteriaValue<Long, Long>? = null,

    @Json(name = "profile_data_filled")
    val profileDataFilled: RankSelectionCriteriaValue<Boolean, Boolean>? = null,

    @Json(name = "sharing_count")
    val sharingCount: RankSelectionCriteriaValue<Long, Long>? = null,

    @Json(name = "order_comment_count")
    val commentsCount: RankSelectionCriteriaValue<Long, Long>? = null,

    @Json(name = "payment_count")
    val paymentsCount: RankSelectionCriteriaValue<Long, Long>? = null,

    @Json(name = "payments_in_money_amount")
    val spentMoney: RankSelectionCriteriaValue<String, String>? = null,

    @Json(name = "collected_cash_back")
    val collectedCashBack: RankSelectionCriteriaValue<String, String>? = null,

    @Json(name = "payments_in_bonuses_amount")
    val spentBonuses: RankSelectionCriteriaValue<Long, Long>? = null,

    @Json(name = "accumulated_bonuses_amount")
    val collectedBonuses: RankSelectionCriteriaValue<Long, Long>? = null
)

@JsonClass(generateAdapter = true)
data class RankPermissionsPrefs(
    @Json(name = "cash_back_percentage")
    val cashBackPercentage: Int? = null,

    @Json(name = "offer_view_allowed")
    val isOfferViewAllowed: Boolean? = null,

    @Json(name = "offer_sharing_allowed")
    val isOfferSharingAllowed: Boolean? = null,

    @Json(name = "article_sharing_allowed")
    val isArticleSharingAllowed: Boolean? = null,

    @Json(name = "pre_order_allowed")
    val isPreOrderAllowed: Boolean? = null,

    @Json(name = "table_reservation_allowed")
    val isTableReservationAllowed: Boolean? = null
)