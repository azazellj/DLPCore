package ua.com.wl.dlp.data.api.responses.models.shop.offer.promo

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

@Deprecated(
    message = "This class will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
data class PromoSettings(
    @SerializedName("promo_type")
    val promoType: PromoType? = null,

    @SerializedName("active_from")
    val activeFrom: String? = null,

    @SerializedName("active_to")
    val activeTo: String? = null,

    @SerializedName("active_since")
    val activeSince: String? = null,

    @SerializedName("active_until")
    val activeUntil: String? = null,

    @SerializedName("promo_params")
    val promoParams: PromoParams? = null)