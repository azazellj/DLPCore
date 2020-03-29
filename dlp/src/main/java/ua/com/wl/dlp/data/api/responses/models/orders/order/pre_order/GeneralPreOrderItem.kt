package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.DeliveryType
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PaymentMethod
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderTradeItem

data class GeneralPreOrderItem(
    @SerializedName("shop")
    val shopId: Int,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("readiness_time")
    val readinessTime: String?,

    @SerializedName("delivery_type")
    val deliveryType: DeliveryType,

    @SerializedName("delivery_address")
    val deliveryAddress: String?,

    @SerializedName("payment_method")
    val paymentMethod: PaymentMethod,

    @SerializedName("pay_with_bonuses")
    val payBonuses: Boolean?,

    @SerializedName("change_from")
    val changeFrom: String?,

    @SerializedName("count_of_bonuses")
    val bonusesCount: Long?,

    @SerializedName("pre_order_trade_items")
    val tradeItems: List<BasePreOrderTradeItem>
) {

    class Builder {

        private var shopId: Int = 0
        private var comment: String? = null
        private var readinessTime: String? = null
        private var deliveryType: DeliveryType = DeliveryType.DELIVERY
        private var deliveryAddress: String? = null
        private var paymentMethod: PaymentMethod = PaymentMethod.CASH
        private var payBonuses: Boolean? = null
        private var changeFrom: String? = null
        private var bonusesCount: Long? = null
        private var tradeItems: MutableList<BasePreOrderTradeItem> = mutableListOf()

        fun shopId(init: () -> Int) {
            shopId = init().also { id ->
                require(id <= 0) {
                    "Shop id must be not equals or less to zero"
                }
            }
        }

        fun comment(init: () -> String?) {
            comment = init()
        }

        fun readinessTime(init: () -> String?) {
            readinessTime = init()?.also { time ->
                require("(?:[01]\\d|2[0-3]):(?:[0-5]\\d)\$".toRegex().matches(time)) {
                    "Invalid time format. Time must be in format HH:mm."
                }
            } ?: return
        }

        fun deliveryType(init: () -> DeliveryType) {
            deliveryType = init()
        }

        fun deliveryAddress(init: () -> String?) {
            deliveryAddress = init() ?: return
        }

        fun paymentMethod(init: () -> PaymentMethod) {
            paymentMethod = init()
        }

        fun payBonuses(init: () -> Boolean?) {
            payBonuses = init() ?: return
        }

        fun changeFrom(init: () -> String?) {
            changeFrom = init()?.also { amount ->
                require("^[\\d]+[\\.][\\d]{2}\$".toRegex().matches(amount)) {
                    "Invalid amount format. Amount must be in format of decimal number with two characters after dot."
                }
            } ?: return
        }

        fun bonusesCount(init: () -> Long?) {
            bonusesCount = init() ?: return
        }

        fun tradeItems(init: (MutableList<BasePreOrderTradeItem>) -> Unit) {
            tradeItems = mutableListOf<BasePreOrderTradeItem>().apply(init)
        }

        fun build(init: Builder.() -> Unit): GeneralPreOrderItem {
            init()
            return GeneralPreOrderItem(
                shopId,
                comment,
                readinessTime,
                deliveryType,
                deliveryAddress,
                paymentMethod,
                payBonuses,
                changeFrom,
                bonusesCount,
                tradeItems
            )
        }
    }
}