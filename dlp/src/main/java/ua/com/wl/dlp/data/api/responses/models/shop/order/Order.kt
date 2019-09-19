package ua.com.wl.dlp.data.api.responses.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class Order(
    @SerializedName("id")
    val id: Int,

    @SerializedName("changed_at")
    val changedAt: String?,

    @SerializedName("price_in_money_total")
    val priceInCurrency: String?,

    @SerializedName("price_in_bonuses_total")
    val priceInBonuses: Long?,

    @SerializedName("shop")
    val shop: OrderShop)