package ua.com.wl.dlp.data.api.responses.models.shop.offer.item

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OfferItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("item_type")
    val type: ItemType?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("photo")
    val photo: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("category")
    val category: ItemCategory?,

    @SerializedName("price_in_money")
    val priceInMoney: String?,

    @SerializedName("price_in_bonuses")
    val priceInBonuses: Long?,

    @SerializedName("cash_back_percentage")
    val cashBackPercentage: Int?)