package ua.com.wl.dlp.data.api.responses.models.consumer.coupons

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class CouponOfferTradeItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("price_in_money")
    val priceInMoney: String,

    @SerializedName("price_in_bonuses")
    val priceInBonuses: Long?,

    @SerializedName("cash_back_percentage")
    val cashBackPercentage: Int?)