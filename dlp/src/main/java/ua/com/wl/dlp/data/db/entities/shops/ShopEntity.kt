package ua.com.wl.dlp.data.db.entities.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.embedded.shop.PreOrderParams

/**
 * @author Denis Makovskyi
 */

@Entity(tableName = ShopEntity.TABLE_NAME)
data class ShopEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "address")
    val address: String? = null,

    @ColumnInfo(name = "schedule")
    val schedule: String? = null,

    @ColumnInfo(name = "thumb_image")
    val thumbImage: String? = null,

    @Embedded
    var preOrderParams: PreOrderParams? = null
) {

    companion object {

        const val TABLE_NAME = "shops"
    }

    @Ignore
    var offers: List<OfferEntity> = emptyList()
}