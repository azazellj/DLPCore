package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BaseOrderResponse constructor(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("rate_value")
    var rate: Int = 0,

    @SerializedName("cash_back_amount")
    var cashback: Long = 0,

    @SerializedName("changed_at")
    var changedAt: String? = null,

    @SerializedName("price_in_bonuses_total")
    var priceInBonuses: Long? = null,

    @SerializedName("price_in_money_total")
    var priceInCurrency: String? = null)