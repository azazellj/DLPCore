package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.models.shop.SimpleShop
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderStatus

@JsonClass(generateAdapter = true)
open class BasePreOrderResponse(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "status")
    var status: PreOrderStatus? = null,

    @Json(name = "created_at")
    var createdAt: String = "",

    @Json(name = "changed_at")
    var changedAt: String = "",

    @Json(name = "readiness_date")
    var readinessDate: String? = null,

    @Json(name = "readiness_time")
    var readinessTime: String? = null,

    @Json(name = "pay_with_bonuses")
    var usedBonuses: Boolean = false,

    @Json(name = "count_of_bonuses")
    var bonusesCount: Long = 0,

    @Json(name = "count_of_bonuses_money")
    var currencyEquivalent: String = "",

    @Json(name = "total_price")
    var totalPrice: String = "",

    @Json(name = "shop")
    var shop: SimpleShop? = null
)