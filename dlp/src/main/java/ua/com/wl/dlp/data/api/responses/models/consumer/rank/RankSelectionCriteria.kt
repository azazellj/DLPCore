package ua.com.wl.dlp.data.api.responses.models.consumer.rank

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.models.consumer.ranks.RankSelectionCriteriaValue

@JsonClass(generateAdapter = true)
data class RankSelectionCriteria(
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

    @Json(name = "payments_in_bonuses_amount")
    val spentBonuses: RankSelectionCriteriaValue<Long, Long>? = null,

    @Json(name = "accumulated_bonuses_amount")
    val collectedBonuses: RankSelectionCriteriaValue<Long, Long>? = null,

    @Json(name = "accumulated_cash_back_amount")
    val collectedCashBack: RankSelectionCriteriaValue<Long, Long>? = null
)