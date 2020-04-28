package ua.com.wl.dlp.data.prefereces.models

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.DeliveryType
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.OperatorCall
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PaymentMethod

/**
 * @author Denis Makovskyi
 */

data class OrderPrefs(
    @SerializedName("date")
    val date: String? = null,

    @SerializedName("time")
    val time: String? = null,

    @SerializedName("comment")
    val comment: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("street")
    val streetName: String? = null,

    @SerializedName("house_number")
    val houseNumber: String? = null,

    @SerializedName("entrance")
    val houseEntrance: String? = null,

    @SerializedName("intercom")
    val intercomCode: String? = null,

    @SerializedName("floor")
    val floorNumber: Int? = null,

    @SerializedName("office_number")
    val officeNumber: String? = null,

    @SerializedName("delivery_type")
    val deliveryType: DeliveryType? = null,

    @SerializedName("persons_quantity")
    val personsQuantity: Int? = null,

    @SerializedName("use_bonuses")
    val useBonuses: Boolean? = null,

    @SerializedName("bonuses_amount")
    val bonusesAmount: Long? = null,

    @SerializedName("payment_banknote")
    val paymentBanknote: String? = null,

    @SerializedName("payment_method")
    val paymentMethod: PaymentMethod? = null,

    @SerializedName("operator_call")
    val operatorCall: OperatorCall? = null)