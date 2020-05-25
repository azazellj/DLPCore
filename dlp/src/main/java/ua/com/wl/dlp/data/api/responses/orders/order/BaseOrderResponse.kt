package ua.com.wl.dlp.data.api.responses.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.shop.BaseSimpleShop
import ua.com.wl.dlp.data.api.responses.models.orders.order.ConsumerOrder
import ua.com.wl.dlp.data.api.responses.models.orders.order.rate.OrderRating

/**
 * @author Denis Makovskyi
 */

open class BaseOrderResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("opened_at")
    var openedAt: String = "",

    @SerializedName("closed_at")
    var closedAt: String = "",

    @SerializedName("cash_back_amount_in_bonuses_money")
    var moneyCashBack: String? = null,

    @SerializedName("cash_back_amount_in_bonuses")
    var bonusesCashBack: Long? = null,

    @SerializedName("shop")
    var shop: BaseSimpleShop? = null,

    @SerializedName("rating")
    var rating: OrderRating? = null,

    @SerializedName("rs_order")
    var consumerOrder: ConsumerOrder? = null)