package ua.com.wl.dlp.data.api.responses.models.shop.promo

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PromoParams(@SerializedName("promo_price_in_bonuses") val priceInBonuses: Long)