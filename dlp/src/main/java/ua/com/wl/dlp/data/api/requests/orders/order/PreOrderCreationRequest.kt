package ua.com.wl.dlp.data.api.requests.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.order.DeliveryType
import ua.com.wl.dlp.data.api.models.order.PaymentMethod
import ua.com.wl.dlp.data.api.models.order.BasePreOrderTradeItem

/**
 * @author Denis Makovskyi
 */

open class PreOrderCreationRequest constructor(
    @SerializedName("shop")
    var shopId: Int = 0,

    @SerializedName("readiness_time")
    var time: String? = null,

    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("delivery_type")
    var deliveryType: DeliveryType? = null,

    @SerializedName("delivery_address")
    var address: String? = null,

    @SerializedName("payment_method")
    var paymentMethod: PaymentMethod? = null,

    @SerializedName("change_from")
    var changeFrom: String? = null,

    @SerializedName("pay_with_bonuses")
    var useBonuses: Boolean? = null,

    @SerializedName("count_of_bonuses")
    var bonusesCount: Long? = null,

    @SerializedName("pre_order_trade_items")
    var tradeItems: List<BasePreOrderTradeItem>? = null)

fun preOrder(
    init: PreOrderCreationRequest.() -> Unit
): PreOrderCreationRequest = PreOrderCreationRequest().apply(init)