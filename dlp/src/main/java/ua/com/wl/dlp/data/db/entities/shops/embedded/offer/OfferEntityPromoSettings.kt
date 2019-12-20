package ua.com.wl.dlp.data.db.entities.shops.embedded.offer

import androidx.room.ColumnInfo
import androidx.room.Embedded

import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoType

/**
 * @author Denis Makovskyi
 */

data class OfferEntityPromoSettings constructor(
    @ColumnInfo(name = "promo_type")
    val promoType: PromoType? = null,

    @ColumnInfo(name = "active_since")
    val activeSince: String? = null,

    @ColumnInfo(name = "active_until")
    val activeUntil: String? = null,

    @ColumnInfo(name = "active_from")
    val activeFrom: String? = null,

    @ColumnInfo(name = "active_to")
    val activeTo: String? = null,

    @Embedded(prefix = "params_")
    var promoParams: OfferEntityPromoParams? = null)