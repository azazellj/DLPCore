package ua.com.wl.dlp.data.db.dao.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.OfferEntity

/**
 * @author Denis Makovskyi
 */

@Dao
interface OffersDao {

    @Query("SELECT * FROM ${OfferEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getOffer(id: Int): OfferEntity?

    @Query("SELECT * FROM ${OfferEntity.TABLE_NAME}")
    suspend fun getOffers(): List<OfferEntity>

    @Query("SELECT * FROM ${OfferEntity.TABLE_NAME} WHERE shop_id = :shopId")
    suspend fun getShopOffers(shopId: Int): List<OfferEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertOffer(offer: OfferEntity): Long

    @Query("DELETE FROM ${OfferEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteOffer(id: Int): Int

    @Delete
    suspend fun deleteOffer(offer: OfferEntity): Int

    @Query("DELETE FROM ${OfferEntity.TABLE_NAME}")
    suspend fun deleteOffers(): Int

    @Query("DELETE FROM ${OfferEntity.TABLE_NAME} WHERE shop_id = :shopId")
    suspend fun deleteShopOffers(shopId: Int): Int
}