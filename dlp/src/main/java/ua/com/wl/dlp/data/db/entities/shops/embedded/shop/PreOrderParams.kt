package ua.com.wl.dlp.data.db.entities.shops.embedded.shop

import androidx.room.ColumnInfo

import ua.com.wl.dlp.data.api.models.shop.order.DeliveryType
import ua.com.wl.dlp.data.api.models.shop.order.PaymentMethod

/**
 * @author Denis Makovskyi
 */

data class PreOrderParams(
    @ColumnInfo(name = "time")
    var time: String? = null,

    @ColumnInfo(name = "address")
    var address: String? = null,

    @ColumnInfo(name = "delivery_type")
    var deliveryType: DeliveryType? = null,

    @ColumnInfo(name = "payment_method")
    var paymentMethod: PaymentMethod? = null,

    @ColumnInfo(name = "change_from_amount")
    var changeFromAmount: String? = null,

    @ColumnInfo(name = "use_bonuses")
    var useBonuses: Boolean? = null,

    @ColumnInfo(name = "bonuses_amount")
    var bonusesAmount: Long? = null,

    @ColumnInfo(name = "comment")
    var comment: String? = null)