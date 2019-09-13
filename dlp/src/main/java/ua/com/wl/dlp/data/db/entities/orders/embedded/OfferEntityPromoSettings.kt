package ua.com.wl.dlp.data.db.entities.orders.embedded

import androidx.room.ColumnInfo
import androidx.room.Embedded

import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoType

/**
 * @author Denis Makovskyi
 */

data class OfferEntityPromoSettings(
    @ColumnInfo(name = "promo_type")
    var promoType: PromoType? = null,

    @ColumnInfo(name = "active_since")
    var activeSince: String? = null,

    @ColumnInfo(name = "active_until")
    var activeUntil: String? = null,

    @ColumnInfo(name = "active_from")
    var activeFrom: String? = null,

    @ColumnInfo(name = "active_to")
    var activeTo: String? = null,

    @Embedded
    var promoParams: OfferEntityPromoParams? = null)