package ua.com.wl.dlp.data.api.responses.models.consumer.rank

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.consumer.ranks.RankSelectionCriteriaValue

/**
 * @author Denis Makovskyi
 */

data class RankSelectionCriteria(
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
    val collectedBonuses: RankSelectionCriteriaValue<Long, Long>? = null,

    @SerializedName("accumulated_cash_back_amount")
    val collectedCashBack: RankSelectionCriteriaValue<Long, Long>? = null)