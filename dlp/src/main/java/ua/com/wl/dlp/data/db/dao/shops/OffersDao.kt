package ua.com.wl.dlp.data.db.dao.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.OfferEntity

/**
 * @author Denis Makovskyi
 */

@Dao
interface OffersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOffer(offer: OfferEntity): Long

    @Update
    suspend fun updateOffer(offer: OfferEntity): Int

    @Delete
    suspend fun deleteOffer(offer: OfferEntity): Int

    @Query("DELETE FROM ${OfferEntity.TABLE_NAME} WHERE 1 = 1")
    suspend fun deleteOffers(): Int
}