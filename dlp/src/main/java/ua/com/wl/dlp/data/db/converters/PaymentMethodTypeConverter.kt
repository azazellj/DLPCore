package ua.com.wl.dlp.data.db.converters

import androidx.room.TypeConverter
import ua.com.wl.dlp.data.api.models.shop.order.PaymentMethod

/**
 * @author Denis Makovskyi
 */

class PaymentMethodTypeConverter {

    @TypeConverter
    fun paymentMethodToString(paymentMethod: PaymentMethod?): String? = paymentMethod?.name

    @TypeConverter
    fun stringToPaymentMethod(paymentMethod: String?): PaymentMethod? = paymentMethod?.let { PaymentMethod.valueOf(it) }
}