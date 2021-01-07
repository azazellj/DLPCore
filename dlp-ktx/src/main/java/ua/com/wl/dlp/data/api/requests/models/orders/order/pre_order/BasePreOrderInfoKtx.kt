package ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.DeliveryType
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.OperatorCall
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PaymentMethod

class BasePreOrderInfoBuilder {
    private var deliveryType: DeliveryType = DeliveryType.TAKEAWAY
    private var streetName: String? = null
    private var houseNumber: String? = null
    private var houseEntrance: String? = null
    private var intercomCode: String? = null
    private var floorNumber: Int? = null
    private var officeNumber: String? = null
    private var personsQuantity: Int = 1
    private var paymentMethod: PaymentMethod = PaymentMethod.CASH
    private var paymentBanknote: String? = null
    private var operatorCall: OperatorCall = OperatorCall.WAITING

    fun deliveryType(init: () -> DeliveryType) {
        deliveryType = init()
    }

    fun streetName(init: () -> String?) {
        streetName = init()
    }

    fun houseNumber(init: () -> String?) {
        houseNumber = init()
    }

    fun houseEntrance(init: () -> String?) {
        houseEntrance = init()
    }

    fun intercomCode(init: () -> String?) {
        intercomCode = init()
    }

    fun floorNumber(init: () -> Int?) {
        floorNumber = init()
    }

    fun officeNumber(init: () -> String?) {
        officeNumber = init()
    }

    fun personsQuantity(init: () -> Int) {
        personsQuantity = init()
    }

    fun paymentMethod(init: () -> PaymentMethod) {
        paymentMethod = init()
    }

    fun paymentBanknote(init: () -> String?) {
        paymentBanknote = init()
    }

    fun operatorCall(init: () -> OperatorCall) {
        operatorCall = init()
    }

    fun build(init: BasePreOrderInfoBuilder.() -> Unit): BasePreOrderInfo {
        init()
        return BasePreOrderInfo(
            deliveryType, streetName, houseNumber, houseEntrance,
            intercomCode, floorNumber, officeNumber, personsQuantity,
            paymentMethod, paymentBanknote, operatorCall
        )
    }
}

fun basePreOrderInfo(init: BasePreOrderInfoBuilder.() -> Unit): BasePreOrderInfo {
    return BasePreOrderInfoBuilder().build(init)
}