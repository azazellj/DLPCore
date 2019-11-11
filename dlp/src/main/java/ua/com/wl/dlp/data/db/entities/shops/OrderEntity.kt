package ua.com.wl.dlp.data.db.entities.shops

import androidx.room.*

/**
 * @author Denis Makovskyi
 */

@Entity(
    tableName = OrderEntity.TABLE_NAME,
    primaryKeys = ["shop_id", "offer_id"],
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
    ],
    indices = [
        Index(
            value = ["shop_id", "offer_id"],
            unique = true)
    ]
)
data class OrderEntity(
    @ColumnInfo(name = "shop_id")
    val shopId: Int,

    @ColumnInfo(name = "offer_id")
    val offerId: Int,

    @ColumnInfo(name = "pre_orders_count")
    val preOrdersCount: Int = 0
) {
    companion object {

        const val TABLE_NAME = "orders"
    }
}