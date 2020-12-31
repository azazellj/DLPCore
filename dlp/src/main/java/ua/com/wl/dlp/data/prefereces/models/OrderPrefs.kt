package ua.com.wl.dlp.data.prefereces.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.DeliveryType
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.OperatorCall
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PaymentMethod

@JsonClass(generateAdapter = true)
data class OrderPrefs(
    @Json(name = "date")
    val date: String? = null,

    @Json(name = "time")
    val time: String? = null,

    @Json(name = "comment")
    val comment: String? = null,

    @Json(name = "address")
    val address: String? = null,

    @Json(name = "street")
    val streetName: String? = null,

    @Json(name = "house_number")
    val houseNumber: String? = null,

    @Json(name = "entrance")
    val houseEntrance: String? = null,

    @Json(name = "intercom")
    val intercomCode: String? = null,

    @Json(name = "floor")
    val floorNumber: Int? = null,

    @Json(name = "office_number")
    val officeNumber: String? = null,

    @Json(name = "delivery_type")
    val deliveryType: DeliveryType? = null,

    @Json(name = "persons_quantity")
    val personsQuantity: Int? = null,

    @Json(name = "use_bonuses")
    val useBonuses: Boolean? = null,

    @Json(name = "bonuses_amount")
    val bonusesAmount: Long? = null,

    @Json(name = "payment_banknote")
    val paymentBanknote: String? = null,

    @Json(name = "payment_method")
    val paymentMethod: PaymentMethod? = null,

    @Json(name = "operator_call")
    val operatorCall: OperatorCall? = null
)