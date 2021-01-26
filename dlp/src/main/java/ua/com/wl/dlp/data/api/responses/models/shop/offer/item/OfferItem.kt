package ua.com.wl.dlp.data.api.responses.models.shop.offer.item

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class OfferItem(
    @Json(name = "id")
    val id: Int,

    @Json(name = "item_type")
    val type: ItemType?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "photo")
    val photo: String?,

    @Json(name = "price")
    val price: String?,

    @Json(name = "category")
    val category: ItemCategory?,

    @Json(name = "price_in_money")
    val priceInMoney: String?,

    @Json(name = "price_in_bonuses")
    val priceInBonuses: Long?,

    @Json(name = "cash_back_percentage")
    val cashBackPercentage: Int?
) : Parcelable