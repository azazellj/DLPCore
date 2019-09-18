package ua.com.wl.dlp.data.db.entities.shops.embedded.offer

import androidx.room.ColumnInfo

/**
 * @author Denis Makovskyi
 */

data class OfferEntityPromoParams(
    @ColumnInfo(name = "gift_name")
    var giftName: String? = null,

    @ColumnInfo(name = "sale_price")
    var salePrice: String? = null,

    @ColumnInfo(name = "event_price")
    var eventPrice: String? = null,

    @ColumnInfo(name = "event_place")
    var eventPlace: String? = null,

    @ColumnInfo(name = "discount_size")
    var discountSize: Long? = null,

    @ColumnInfo(name = "discount_price")
    var discountPrice: String? = null,

    @ColumnInfo(name = "price_in_bonuses")
    var priceInBonuses: Long? = null,

    @ColumnInfo(name = "Cashback_size")
    var cashbackSize: Long? = null)