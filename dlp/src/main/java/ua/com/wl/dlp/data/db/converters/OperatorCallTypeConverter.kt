package ua.com.wl.dlp.data.db.converters

import androidx.room.TypeConverter

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.OperatorCall

/**
 * @author Denis Makovskyi
 */

class OperatorCallTypeConverter {

    @TypeConverter
    fun operatorCallToString(operatorCall: OperatorCall?): String? = operatorCall?.name

    @TypeConverter
    fun stringToOperatorCall(operatorCall: String?): OperatorCall? = operatorCall?.let { OperatorCall.valueOf(it) }
}