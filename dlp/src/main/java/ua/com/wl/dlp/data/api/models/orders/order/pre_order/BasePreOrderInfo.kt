package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BasePreOrderInfo(
    @SerializedName("delivery_type")
    var deliveryType: DeliveryType = DeliveryType.TAKEAWAY,

    @SerializedName("street")
    var streetName: String? = null,

    @SerializedName("house_number")
    var houseNumber: String? = null,

    @SerializedName("entrance")
    var houseEntrance: String? = null,

    @SerializedName("intercom")
    var intercomCode: String? = null,

    @SerializedName("floor")
    var floorNumber: Int? = null,

    @SerializedName("office_number")
    var officeNumber: String? = null,

    @SerializedName("persons_quantity")
    var personsQuantity: Int? = null,

    @SerializedName("payment_method")
    var paymentMethod: PaymentMethod = PaymentMethod.CASH,

    @SerializedName("payment_banknote")
    var paymentBanknote: String? = null,

    @SerializedName("operator_call")
    var operatorCall: OperatorCall = OperatorCall.WAITING
) {

    class Builder {

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

        fun build(init: Builder.() -> Unit): BasePreOrderInfo {
            init()
            return BasePreOrderInfo(
                deliveryType, streetName, houseNumber, houseEntrance,
                intercomCode, floorNumber, officeNumber, personsQuantity,
                paymentMethod, paymentBanknote, operatorCall)
        }
    }
}