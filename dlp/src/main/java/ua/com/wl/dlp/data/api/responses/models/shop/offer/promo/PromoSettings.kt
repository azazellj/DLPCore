package ua.com.wl.dlp.data.api.responses.models.shop.offer.promo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Deprecated(message = "This class will be removed in further revisions. Changes are caused by offer promo structure refactoring.")
@JsonClass(generateAdapter = true)
data class PromoSettings(
    @Json(name = "promo_type")
    val promoType: PromoType? = null,

    @Json(name = "active_from")
    val activeFrom: String? = null,

    @Json(name = "active_to")
    val activeTo: String? = null,

    @Json(name = "active_since")
    val activeSince: String? = null,

    @Json(name = "active_until")
    val activeUntil: String? = null,

    @Json(name = "promo_params")
    val promoParams: PromoParams? = null
)