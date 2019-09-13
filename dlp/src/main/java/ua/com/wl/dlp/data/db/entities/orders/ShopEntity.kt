package ua.com.wl.dlp.data.db.entities.orders

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Denis Makovskyi
 */

@Entity(tableName = ShopEntity.TABLE_NAME)
data class ShopEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "date")
    var date: Long,

    @ColumnInfo(name = "comment")
    var comment: String? = null
) {

    companion object {

        const val TABLE_NAME = "shops"
    }
}