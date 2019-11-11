package ua.com.wl.dlp.data.db.dao.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.OrderEntity

/**
 * @author Denis Makovskyi
 */

@Dao
interface OrdersDao {

    @Query("SELECT COUNT(offer_id) FROM ${OrderEntity.TABLE_NAME} WHERE offer_id= :offerId")
    suspend fun getOrderEntries(offerId: Int): Int

    @Query("""
        SELECT * FROM ${OfferEntity.TABLE_NAME} OffersTable
        INNER JOIN ${OrderEntity.TABLE_NAME} OrdersTable
        ON OffersTable.id=OrdersTable.offer_id
        WHERE OrdersTable.shop_id= :shopId
        AND OffersTable.id= :offerId
        """)
    suspend fun getOfferForShop(shopId: Int, offerId: Int): OfferEntity?

    @Query(
        """
        SELECT * FROM ${OfferEntity.TABLE_NAME} OffersTable
        INNER JOIN ${OrderEntity.TABLE_NAME} OrdersTable
        ON OffersTable.id=OrdersTable.offer_id
        WHERE OrdersTable.shop_id= :shopId
        """)
    suspend fun getOffersForShop(shopId: Int): List<OfferEntity>

    @Query("SELECT * FROM ${OrderEntity.TABLE_NAME} WHERE shop_id= :shopId AND offer_id= :offerId")
    suspend fun getOrderRelation(shopId: Int, offerId: Int): OrderEntity?

    @Insert
    suspend fun upsertOrderRelation(entity: OrderEntity): Long

    @Query("DELETE FROM ${OrderEntity.TABLE_NAME} WHERE shop_id= :shopId AND offer_id= :offerId")
    suspend fun deleteOrderRelation(shopId: Int, offerId: Int): Int

    @Query("DELETE FROM ${OrderEntity.TABLE_NAME} WHERE 1 = 1")
    suspend fun deleteOrderRelations(): Int
}