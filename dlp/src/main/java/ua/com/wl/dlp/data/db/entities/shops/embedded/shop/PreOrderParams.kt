package ua.com.wl.dlp.data.db.entities.shops.embedded.shop

import androidx.room.ColumnInfo

import ua.com.wl.dlp.data.api.models.order.DeliveryType
import ua.com.wl.dlp.data.api.models.order.PaymentMethod

/**
 * @author Denis Makovskyi
 */

data class PreOrderParams(
    @ColumnInfo(name = "time")
    val time: String? = null,

    @ColumnInfo(name = "address")
    val address: String? = null,

    @ColumnInfo(name = "delivery_type")
    val deliveryType: DeliveryType? = null,

    @ColumnInfo(name = "payment_method")
    val paymentMethod: PaymentMethod? = null,

    @ColumnInfo(name = "change_from_amount")
    val changeFromAmount: String? = null,

    @ColumnInfo(name = "use_bonuses")
    val useBonuses: Boolean? = null,

    @ColumnInfo(name = "bonuses_amount")
    val bonusesAmount: Long? = null,

    @ColumnInfo(name = "comment")
    val comment: String? = null)