package ua.com.wl.dlp.data.db.entities.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoSettings

/**
 * @author Denis Makovskyi
 */

@Entity(tableName = OfferEntity.TABLE_NAME)
data class OfferEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "trade_item")
    val tradeItem: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "thumb_image")
    val thumbImage: String? = null,

    @ColumnInfo(name = "short_description")
    val shortDescription: String? = null,

    @ColumnInfo(name = "price_in_bonuses")
    val priceInBonuses: Long? = null,

    @ColumnInfo(name = "price_in_currency")
    val priceInCurrency: String? = null,

    @ColumnInfo(name = "cashback_percentage")
    val cashbackPercentage: Int? = null,

    @ColumnInfo(name = "is_promo_offer")
    val isPromoOffer: Boolean = false,

    @ColumnInfo(name = "is_favourite_offer")
    val isFavouriteOffer: Boolean = false,

    @Embedded(prefix = "settings_")
    var promoSettings: OfferEntityPromoSettings? = null
) {
    companion object {

        const val TABLE_NAME = "offers"
    }

    @Ignore
    @ColumnInfo(name = "shop_id_ignored")
    var shopId: Int = 0

    @Ignore
    @ColumnInfo(name = "pre_orders_count_ignored")
    var preOrdersCount: Int = 0
}