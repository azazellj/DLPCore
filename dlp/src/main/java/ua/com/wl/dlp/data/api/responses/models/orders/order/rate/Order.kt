package ua.com.wl.dlp.data.api.responses.models.orders.order.rate

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

import ua.com.wl.dlp.data.api.models.shop.SimpleShop

@JsonClass(generateAdapter = true)
@Parcelize
data class Order(
    @Json(name = "id")
    val id: Int,

    @Json(name = "cash_back_amount")
    var cashback: Long? = null,

    @Json(name = "changed_at")
    val changedAt: String?,

    @Json(name = "price_in_money_total")
    val priceInCurrency: String?,

    @Json(name = "price_in_bonuses_total")
    val priceInBonuses: Long?,

    @Json(name = "shop")
    val shop: SimpleShop
) : Parcelable