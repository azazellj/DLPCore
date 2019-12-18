package ua.com.wl.dlp.data.db.converters

import androidx.room.TypeConverter

import ua.com.wl.dlp.data.api.models.order.DeliveryType

/**
 * @author Denis Makovskyi
 */

class DeliveryTypeConverter {

    @TypeConverter
    fun deliveryTypeToString(deliveryType: DeliveryType?): String? = deliveryType?.name

    @TypeConverter
    fun stringTypeToString(deliveryType: String?): DeliveryType? = deliveryType?.let { DeliveryType.valueOf(it) }
}