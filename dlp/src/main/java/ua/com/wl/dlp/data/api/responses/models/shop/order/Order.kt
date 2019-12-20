package ua.com.wl.dlp.data.api.responses.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class Order constructor(
    @SerializedName("id")
    val id: Int,

    @SerializedName("cash_back_amount")
    var cashback: Long? = null,

    @SerializedName("changed_at")
    val changedAt: String?,

    @SerializedName("price_in_money_total")
    val priceInCurrency: String?,

    @SerializedName("price_in_bonuses_total")
    val priceInBonuses: Long?,

    @SerializedName("shop")
    val shop: OrderShop)