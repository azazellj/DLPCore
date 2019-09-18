package ua.com.wl.dlp.data.db.entities.shops

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoSettings

/**
 * @author Denis Makovskyi
 */

@Entity(
    tableName = OfferEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ShopEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("shop_id"),
            onDelete = CASCADE)
    ]
)
data class OfferEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "shop_id")
    var shopId: Int,

    @ColumnInfo(name = "trade_item")
    var tradeItem: Int? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "thumb_image")
    var thumbImage: String? = null,

    @ColumnInfo(name = "short_description")
    var shortDescription: String? = null,

    @ColumnInfo(name = "price_in_bonuses")
    var priceInBonuses: Long? = null,

    @ColumnInfo(name = "price_in_currency")
    var priceInCurrency: String? = null,

    @ColumnInfo(name = "cashback_percentage")
    var cashbackPercentage: Int? = null,

    @ColumnInfo(name = "is_promo_offer")
    var isPromoOffer: Boolean = false,

    @ColumnInfo(name = "is_favourite_offer")
    var isFavouriteOffer: Boolean = false,

    @ColumnInfo(name = "pre_order_count")
    var preOrderCount: Int = 0,

    @Embedded(prefix = "settings_")
    var promoSettings: OfferEntityPromoSettings? = null
) {
    companion object {

        const val TABLE_NAME = "offers"
    }
}