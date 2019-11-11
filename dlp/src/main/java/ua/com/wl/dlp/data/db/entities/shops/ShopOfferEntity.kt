package ua.com.wl.dlp.data.db.entities.shops

import androidx.room.*

/**
 * @author Denis Makovskyi
 */

@Entity(
    tableName = ShopOfferEntity.TABLE_NAME,
    indices = [
        Index(
            value = ["id"],
            unique = true),
        Index(
            value = ["shop_id", "offer_id"],
            unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = ShopEntity::class,
            parentColumns = ["id"],
            childColumns = ["shop_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = OfferEntity::class,
            parentColumns = ["id"],
            childColumns = ["offer_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShopOfferEntity(
    @ColumnInfo(name = "shop_id")
    val shopId: Int,

    @ColumnInfo(name = "offer_id")
    val offerId: Int,

    @ColumnInfo(name = "pre_orders_count")
    val preOrdersCount: Int = 0
) {
    companion object {

        const val TABLE_NAME = "shop_offers"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}