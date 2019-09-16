package ua.com.wl.dlp.data.api.responses.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PreOrderResponse(
    @SerializedName("readiness_date") val readinessDate: String,
    @SerializedName("readiness_time") val readinessTime: String,
    @SerializedName("pay_with_bonuses") val usedBonuses: Boolean,
    @SerializedName("count_of_bonuses") val bonusesCount: Long) : BasePreOrderResponse()