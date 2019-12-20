package ua.com.wl.dlp.data.db.entities.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.embedded.shop.PreOrderParams

/**
 * @author Denis Makovskyi
 */

@Entity(tableName = ShopEntity.TABLE_NAME)
data class ShopEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @Embedded
    var preOrderParams: PreOrderParams? = null
) {

    companion object {

        const val TABLE_NAME = "shops"
    }

    @Ignore
    var offers: List<OfferEntity> = emptyList()
}