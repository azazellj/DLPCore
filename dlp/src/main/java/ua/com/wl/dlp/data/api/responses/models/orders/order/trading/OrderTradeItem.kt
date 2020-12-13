package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderTradeItem(
    @Json(name = "id")
    val id: Int,

    @Json(name = "price_in_money")
    val priceInMoney: String?,

    @Json(name = "price_in_bonuses")
    val priceInBonuses: Long?,

    @Json(name = "products_qty_in_money")
    val productsQtyInMoney: String?,

    @Json(name = "products_qty_in_bonuses")
    val productsQtyInBonuses: String?,

    @Json(name = "total_price_in_money")
    val totalPriceInMoney: String?,

    @Json(name = "total_price_in_bonuses")
    val totalPriceInBonuses: String?,

    @Json(name = "total_price_in_bonuses_money")
    val totalPriceInBonusesMoney: String?,

    @Json(name = "offer")
    val offer: OrderOffer
)