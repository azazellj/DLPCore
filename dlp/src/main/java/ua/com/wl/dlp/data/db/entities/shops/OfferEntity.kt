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
    ],
    indices = [
        Index(
            value = ["id", "shop_id"],
            unique = true)
    ]
)
data class OfferEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "unique_id")
    val uniqueId: Long,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "shop_id")
    val shopId: Int,

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

    @ColumnInfo(name = "pre_order_count")
    val preOrderCount: Int = 0,

    @Embedded(prefix = "settings_")
    var promoSettings: OfferEntityPromoSettings? = null
) {
    companion object {

        const val TABLE_NAME = "offers"
    }
}