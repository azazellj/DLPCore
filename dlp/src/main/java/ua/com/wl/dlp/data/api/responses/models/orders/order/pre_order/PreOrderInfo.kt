package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.DeliveryType
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.OperatorCall
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PaymentMethod

@JsonClass(generateAdapter = true)
data class PreOrderInfo(

    // BasePreOrderInfo fields

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
    var operatorCall: OperatorCall = OperatorCall.WAITING,

    // PreOrderInfo fields
    @Json(name = "payment_status")
    val paymentStatus: PaymentStatus? = null
)