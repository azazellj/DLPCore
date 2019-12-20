package ua.com.wl.dlp.data.db.entities.shops.embedded.offer

import androidx.room.ColumnInfo

/**
 * @author Denis Makovskyi
 */

data class OfferEntityPromoParams constructor(
    @ColumnInfo(name = "gift_name")
    val giftName: String? = null,

    @ColumnInfo(name = "sale_price")
    val salePrice: String? = null,

    @ColumnInfo(name = "event_price")
    val eventPrice: String? = null,

    @ColumnInfo(name = "event_place")
    val eventPlace: String? = null,

    @ColumnInfo(name = "discount_size")
    val discountSize: Long? = null,

    @ColumnInfo(name = "discount_price")
    val discountPrice: String? = null,

    @ColumnInfo(name = "price_in_bonuses")
    val priceInBonuses: Long? = null,

    @ColumnInfo(name = "Cashback_size")
    val cashbackSize: Long? = null)