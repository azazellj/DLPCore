package ua.com.wl.dlp.data.db.dao.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopOfferEntity

/**
 * @author Denis Makovskyi
 */

@Dao
interface ShopsOffersDao {

    @Query("SELECT COUNT(offer_id) FROM ${ShopOfferEntity.TABLE_NAME} WHERE offer_id= :offerId")
    suspend fun getOfferEntries(offerId: Int): Int

    @Query("""
        SELECT * FROM ${OfferEntity.TABLE_NAME} OffersTable
        INNER JOIN ${ShopOfferEntity.TABLE_NAME} ShopsOffersTable
        ON OffersTable.id=ShopsOffersTable.offer_id
        WHERE ShopsOffersTable.shop_id= :shopId
        AND OffersTable.id= :offerId
        """)
    suspend fun getOfferForShop(shopId: Int, offerId: Int): OfferEntity?

    @Query("""
        SELECT * FROM ${OfferEntity.TABLE_NAME} OffersTable
        INNER JOIN ${ShopOfferEntity.TABLE_NAME} ShopsOffersTable
        ON OffersTable.id=ShopsOffersTable.offer_id
        WHERE ShopsOffersTable.shop_id= :shopId
        """)
    suspend fun getOffersForShop(shopId: Int): List<OfferEntity>

    @Query("SELECT * FROM ${ShopOfferEntity.TABLE_NAME} WHERE shop_id= :shopId AND offer_id= :offerId")
    suspend fun getRelation(shopId: Int, offerId: Int): ShopOfferEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRelation(entity: ShopOfferEntity): Long

    @Query("DELETE FROM ${ShopOfferEntity.TABLE_NAME} WHERE shop_id= :shopId AND offer_id= :offerId")
    suspend fun deleteRelation(shopId: Int, offerId: Int): Int

    @Query("DELETE FROM ${ShopOfferEntity.TABLE_NAME} WHERE 1 = 1")
    suspend fun deleteRelations(): Int
}