package ua.com.wl.dlp.data.api.responses.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderTradeItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("count_in_bonuses")
    val soldInBonuses: Double,

    @SerializedName("price_in_bonuses")
    val priceInBonuses: Long?,

    @SerializedName("price_in_bonuses_total")
    val totalPriceInBonuses: Long?,

    @SerializedName("count_in_money")
    val soldInCurrency: Double,

    @SerializedName("price_in_uah")
    val priceInCurrency: String?,

    @SerializedName("price_in_money_total")
    val totalPriceInCurrency: String?,

    @SerializedName("offer")
    val offer: OrderOffer)