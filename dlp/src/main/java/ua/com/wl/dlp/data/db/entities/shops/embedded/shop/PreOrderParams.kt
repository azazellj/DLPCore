package ua.com.wl.dlp.data.db.entities.shops.embedded.shop

import androidx.room.ColumnInfo

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.DeliveryType
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.OperatorCall
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PaymentMethod

/**
 * @author Denis Makovskyi
 */

data class PreOrderParams(
    @ColumnInfo(name = "date")
    val date: String? = null,

    @ColumnInfo(name = "time")
    val time: String? = null,

    @ColumnInfo(name = "comment")
    val comment: String? = null,

    @ColumnInfo(name = "street")
    val streetName: String? = null,

    @ColumnInfo(name = "house_number")
    val houseNumber: String? = null,

    @ColumnInfo(name = "entrance")
    val houseEntrance: String? = null,

    @ColumnInfo(name = "intercom")
    val intercomCode: String? = null,

    @ColumnInfo(name = "floor")
    val floorNumber: Int? = null,

    @ColumnInfo(name = "office_number")
    val officeNumber: String? = null,

    @ColumnInfo(name = "delivery_type")
    val deliveryType: DeliveryType? = null,

    @ColumnInfo(name = "persons_quantity")
    val personsQuantity: Int? = null,

    @ColumnInfo(name = "use_bonuses")
    val useBonuses: Boolean? = null,

    @ColumnInfo(name = "bonuses_amount")
    val bonusesAmount: Long? = null,

    @ColumnInfo(name = "payment_banknote")
    val paymentBanknote: String? = null,

    @ColumnInfo(name = "payment_method")
    val paymentMethod: PaymentMethod? = null,

    @ColumnInfo(name = "operator_call")
    val operatorCall: OperatorCall? = null)