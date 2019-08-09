package ua.com.wl.dlp.data.api.responses.models.shop.promo

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PromoSettings(
    @SerializedName("promo_type") val promoType: PromoType,
    @SerializedName("active_from") val activeFrom: String?,
    @SerializedName("active_to") val activeTo: String?,
    @SerializedName("active_since") val activeSince: String?,
    @SerializedName("active_until") val activeUntil: String?)