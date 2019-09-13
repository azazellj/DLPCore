package ua.com.wl.dlp.data.db.converters

import androidx.room.TypeConverter

import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoType

/**
 * @author Denis Makovskyi
 */

class PromoTypeConverter {

    @TypeConverter
    fun promoTypeToString(promoType: PromoType?): String? = promoType?.name

    @TypeConverter
    fun stringToPromoType(promoType: String?): PromoType? = promoType?.let { PromoType.valueOf(it) }
}