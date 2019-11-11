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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertOffer(offer: OfferEntity): Long

    @Delete
    suspend fun deleteOffer(offer: OfferEntity): Int

    @Query("DELETE FROM ${OfferEntity.TABLE_NAME} WHERE 1 = 1")
    suspend fun deleteOffers(): Int
}