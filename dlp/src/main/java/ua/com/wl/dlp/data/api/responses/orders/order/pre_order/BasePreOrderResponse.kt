package ua.com.wl.dlp.data.api.responses.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderShop
import ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order.PreOrderStatus

/**
 * @author Denis Makovskyi
 */

open class BasePreOrderResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("status")
    var status: PreOrderStatus? = null,

    @SerializedName("created_at")
    var createdAt: String = "",

    @SerializedName("changed_at")
    var changedAt: String = "",

    @SerializedName("readiness_date")
    var readinessDate: String? = null,

    @SerializedName("readiness_time")
    var readinessTime: String? = null,

    @SerializedName("pay_with_bonuses")
    var usedBonuses: Boolean = false,

    @SerializedName("count_of_bonuses")
    var bonusesCount: Long = 0,

    @SerializedName("count_of_bonuses_money")
    var currencyEquivalent: String = "",

    @SerializedName("total_price")
    var totalPrice: String = "",

    @SerializedName("shop")
    var shop: PreOrderShop? = null)