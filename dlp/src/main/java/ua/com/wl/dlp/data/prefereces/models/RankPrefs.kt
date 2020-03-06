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
    val nextRankCriteria: RankCriteriaPrefs? = null)

data class RankCriteriaPrefs(
    @SerializedName("referral_count")
    val referralCount: RankSelectionCriteriaValue<Int, Int>? = null,

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

    @SerializedName("payments_in_bonuses_amount")
    val spentBonuses: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("accumulated_bonuses_amount")
    val collectedBonuses: RankSelectionCriteriaValue<Long, Long>? = null)