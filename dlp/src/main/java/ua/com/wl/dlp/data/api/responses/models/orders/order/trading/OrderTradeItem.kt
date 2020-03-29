package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderTradeItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("price_in_money")
    val priceInMoney: String?,

    @SerializedName("price_in_bonuses")
    val priceInBonuses: Long?,

    @SerializedName("price_in_money")
    val productsQtyInMoney: String?,

    @SerializedName("products_qty_in_bonuses")
    val productsQtyInBonuses: String?,

    @SerializedName("total_price_in_money")
    val totalPriceInMoney: String?,

    @SerializedName("total_price_in_bonuses")
    val totalPriceInBonuses: String?,

    @SerializedName("total_price_in_bonuses_money")
    val totalPriceInBonusesMoney: String?,

    @SerializedName("offer")
    val offer: OrderOffer)