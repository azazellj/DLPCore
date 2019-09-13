package ua.com.wl.dlp.data.db.dao.orders

import androidx.lifecycle.LiveData
import androidx.room.*

import ua.com.wl.dlp.data.db.entities.orders.ShopEntity

/**
 * @author Denis Makovskyi
 */

@Dao
interface ShopsDao {

    @Query("SELECT * FROM ${ShopEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getShop(id: Int): ShopEntity?

    @Query("SELECT * FROM ${ShopEntity.TABLE_NAME}")
    suspend fun getShops(): LiveData<List<ShopEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertShop(shop: ShopEntity): Long

    @Query("DELETE FROM ${ShopEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteShop(id: Int): Int

    @Delete
    suspend fun deleteShop(shop: ShopEntity): Int

    @Query("DELETE FROM ${ShopEntity.TABLE_NAME}")
    suspend fun deleteShops(): Int
}