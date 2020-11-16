package ua.com.wl.dlp.data.prefereces.models

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.consumer.ranks.RankSelectionCriteriaValue

/**
 * @author Denis Makovskyi
 */

data class RankPrefs(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("icon_url")
    val iconUrl: String? = null,

    @SerializedName("color_hex")
    val colorHex: String? = null,

    @SerializedName("next_rank_criteria")
    val nextRankCriteria: RankCriteriaPrefs? = null,

    @SerializedName("current_rank_permissions")
    val currRankPermissions: RankPermissionsPrefs? = null)

data class RankCriteriaPrefs(
    @SerializedName("referral_count")
    val referralCount: RankSelectionCriteriaValue<Int, Int>? = null,

    @SerializedName("accumulated_cash_back_amount")
    val collectedCashBack: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("days_registered")
    val daysRegistered: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("profile_data_filled")
    val profileDataFilled: RankSelectionCriteriaValue<Boolean, Boolean>? = null,

    @SerializedName("sharing_count")
    val sharingCount: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("order_comment_count")
    val commentsCount: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("payment_count")
    val paymentsCount: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("payments_in_money_amount")
    val spentMoney: RankSelectionCriteriaValue<String, String>? = null,
    
    @SerializedName("collected_cash_back")
    val collectedCashBack: RankSelectionCriteriaValue<String, String>? = null,

    @SerializedName("payments_in_bonuses_amount")
    val spentBonuses: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("accumulated_bonuses_amount")
    val collectedBonuses: RankSelectionCriteriaValue<Long, Long>? = null)



data class RankPermissionsPrefs(
    @SerializedName("cash_back_percentage")
    val cashBackPercentage: Int? = null,

    @SerializedName("offer_view_allowed")
    val isOfferViewAllowed: Boolean? = null,

    @SerializedName("offer_sharing_allowed")
    val isOfferSharingAllowed: Boolean? = null,

    @SerializedName("article_sharing_allowed")
    val isArticleSharingAllowed: Boolean? = null,

    @SerializedName("pre_order_allowed")
    val isPreOrderAllowed: Boolean? = null,

    @SerializedName("table_reservation_allowed")
    val isTableReservationAllowed: Boolean? = null)