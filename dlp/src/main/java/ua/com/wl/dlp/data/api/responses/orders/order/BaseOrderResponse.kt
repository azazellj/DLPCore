package ua.com.wl.dlp.data.api.responses.orders.order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.models.shop.BaseSimpleShop
import ua.com.wl.dlp.data.api.responses.models.orders.order.ConsumerOrder
import ua.com.wl.dlp.data.api.responses.models.orders.order.rate.OrderRating

@JsonClass(generateAdapter = true)
open class BaseOrderResponse(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "opened_at")
    var openedAt: String = "",

    @Json(name = "closed_at")
    var closedAt: String = "",

    @Json(name = "cash_back_amount_in_bonuses_money")
    var moneyCashBack: String? = null,

    @Json(name = "cash_back_amount_in_bonuses")
    var bonusesCashBack: Long? = null,

    @Json(name = "shop")
    var shop: BaseSimpleShop? = null,

    @Json(name = "rating")
    var rating: OrderRating? = null,

    @Json(name = "rs_order")
    var consumerOrder: ConsumerOrder? = null
)