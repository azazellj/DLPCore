package ua.com.wl.dlp.data.api.models.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Denis Makovskyi
 */
@JsonClass(generateAdapter = true)
open class BasePreOrderInfo(
    @Json(name = "delivery_type")
    var deliveryType: DeliveryType = DeliveryType.TAKEAWAY,

    @Json(name = "street")
    var streetName: String? = null,

    @Json(name = "house_number")
    var houseNumber: String? = null,

    @Json(name = "entrance")
    var houseEntrance: String? = null,

    @Json(name = "intercom")
    var intercomCode: String? = null,

    @Json(name = "floor")
    var floorNumber: Int? = null,

    @Json(name = "office_number")
    var officeNumber: String? = null,

    @Json(name = "persons_quantity")
    var personsQuantity: Int? = null,

    @Json(name = "payment_method")
    var paymentMethod: PaymentMethod = PaymentMethod.CASH,

    @Json(name = "payment_banknote")
    var paymentBanknote: String? = null,

    @Json(name = "operator_call")
    var operatorCall: OperatorCall = OperatorCall.WAITING
) {

    // TODO: 12/12/20 separate methods
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
                paymentMethod, paymentBanknote, operatorCall
            )
        }
    }
}