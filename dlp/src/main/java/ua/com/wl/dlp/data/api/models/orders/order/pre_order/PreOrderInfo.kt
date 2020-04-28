package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PreOrderInfo(
    @SerializedName("street")
    val streetName: String?,

    @SerializedName("house_number")
    val houseNumber: String?,

    @SerializedName("entrance")
    val houseEntrance: String?,

    @SerializedName("intercom")
    val intercomCode: String?,

    @SerializedName("floor")
    val floorNumber: Int?,

    @SerializedName("office_number")
    val officeNumber: String?,

    @SerializedName("delivery_type")
    val deliveryType: DeliveryType,

    @SerializedName("persons_quantity")
    val personsQuantity: Int,

    @SerializedName("payment_banknote")
    val paymentBanknote: String?,

    @SerializedName("payment_method")
    val paymentMethod: PaymentMethod,

    @SerializedName("operator_call")
    val operatorCall: OperatorCall
) {

    class Builder {

        private var streetName: String? = null
        private var houseNumber: String? = null
        private var houseEntrance: String? = null
        private var intercomCode: String? = null
        private var floorNumber: Int? = null
        private var officeNumber: String? = null
        private var deliveryType: DeliveryType = DeliveryType.TAKEAWAY
        private var personsQuantity: Int = 1
        private var paymentBanknote: String? = null
        private var paymentMethod: PaymentMethod = PaymentMethod.ONLINE
        private var operatorCall: OperatorCall = OperatorCall.WAITING

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

        fun deliveryType(init: () -> DeliveryType) {
            deliveryType = init()
        }

        fun personsQuantity(init: () -> Int) {
            personsQuantity = init()
        }

        fun paymentBanknote(init: () -> String?) {
            paymentBanknote = init()
        }

        fun paymentMethod(init: () -> PaymentMethod) {
            paymentMethod = init()
        }

        fun operatorCall(init: () -> OperatorCall) {
            operatorCall = init()
        }

        fun build(init: Builder.() -> Unit): PreOrderInfo {
            init()
            return PreOrderInfo(
                streetName, houseNumber, houseEntrance,
                intercomCode, floorNumber, officeNumber,
                deliveryType, personsQuantity, paymentBanknote, paymentMethod, operatorCall)
        }
    }
}