package ua.com.wl.dlp.data.api.responses.models.orders.order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.order.BasePreOrderTradeItem
import ua.com.wl.dlp.data.api.models.order.DeliveryType
import ua.com.wl.dlp.data.api.models.order.PaymentMethod
import ua.com.wl.dlp.utils.now

class GeneralPreOrderItem constructor(
    @SerializedName("shop")
    var shopId: Int = 0,

    @SerializedName("readiness_time")
    var time: String = now("HH:mm"),

    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("delivery_type")
    var deliveryType: DeliveryType = DeliveryType.TAKEAWAY,

    @SerializedName("delivery_address")
    var deliveryAddress: String? = null,

    @SerializedName("payment_method")
    var paymentMethod: PaymentMethod = PaymentMethod.CASH,

    @SerializedName("pay_with_bonuses")
    var useBonuses: Boolean = false,

    @SerializedName("change_from")
    var changeFrom: String? = null,

    @SerializedName("count_of_bonuses")
    var bonusesCount: Long = 0,

    @SerializedName("pre_order_trade_items")
    var tradeItems: List<BasePreOrderTradeItem>? = null
) {

    fun shopId(init: () -> Int) {
        shopId = init().also { id ->
            require(id <= 0) {
                "Shop id must be not equals or less to zero"
            }
        }
    }

    fun time(init: () -> String) {
        time = init().also { hhMM ->
            require("(?:[01]\\d|2[0-3]):(?:[0-5]\\d)\$".toRegex().matches(hhMM)) {
                "Invalid time format. Time must be in format HH:mm."
            }
        }
    }

    fun comment(init: () -> String?) {
        comment = init()
    }

    fun deliveryType(init: () -> DeliveryType) {
        deliveryType = init()
    }

    fun deliveryAddress(init: () -> String?) {
        deliveryAddress = init()
    }

    fun paymentMethod(init: () -> PaymentMethod) {
        paymentMethod = init()
    }

    fun useBonuses(init: () -> Boolean) {
        useBonuses = init()
    }

    fun changeFrom(init: () -> String?) {
        changeFrom = init()?.also { amount ->
            require("^[\\d]+[\\.][\\d]{2}\$".toRegex().matches(amount)) {
                "Invalid amount format. Amount must be in format of decimal number with two characters after dot."
            }
        }
    }

    fun bonusesCount(init: () -> Long) {
        bonusesCount = init()
    }

    fun tradeItems(init: (MutableList<BasePreOrderTradeItem>) -> Unit) {
        tradeItems = mutableListOf<BasePreOrderTradeItem>().apply(init)
    }
}

fun preOrderItem(
    init: GeneralPreOrderItem.() -> Unit
): GeneralPreOrderItem = GeneralPreOrderItem().apply(init)