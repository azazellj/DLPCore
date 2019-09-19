package ua.com.wl.dlp.data.api.responses.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BaseOrderResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("rate_value")
    var rate: Int = 0,

    @SerializedName("cash_back_amount")
    var cashback: Long = 0,

    @SerializedName("changed_at")
    var changedAt: String? = null,

    @SerializedName("price_in_money_total")
    var priceInCurrency: String? = null,

    @SerializedName("price_in_bonuses_total")
    var priceInBonuses: Long? = null)