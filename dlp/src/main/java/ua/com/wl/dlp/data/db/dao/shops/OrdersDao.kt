package ua.com.wl.dlp.data.db.dao.shops

import androidx.room.*

import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.OrderEntity

/**
 * @author Denis Makovskyi
 */

@Dao
interface OrdersDao {

    @Query("""
        SELECT * FROM ${OfferEntity.TABLE_NAME} OffersTable
        INNER JOIN ${OrderEntity.TABLE_NAME} OrdersTable
        ON OffersTable.id=OrdersTable.offer_id
        WHERE OrdersTable.shop_id= :shopId
        AND OffersTable.id= :offerId
        """)
    suspend fun getOffer(shopId: Int, offerId: Int): OfferEntity?

    @Query(
        """
        SELECT * FROM ${OfferEntity.TABLE_NAME} OffersTable
        INNER JOIN ${OrderEntity.TABLE_NAME} OrdersTable
        ON OffersTable.id=OrdersTable.offer_id
        WHERE OrdersTable.shop_id= :shopId
        """)
    suspend fun getOffers(shopId: Int): List<OfferEntity>

    @Query("SELECT COUNT(offer_id) FROM ${OrderEntity.TABLE_NAME} WHERE offer_id= :offerId")
    suspend fun getCount(offerId: Int): Int

    @Query("SELECT * FROM ${OrderEntity.TABLE_NAME} WHERE shop_id= :shopId AND offer_id= :offerId")
    suspend fun getOrder(shopId: Int, offerId: Int): OrderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(entity: OrderEntity): Long

    @Update
    suspend fun updateOrder(entity: OrderEntity): Int

    @Delete
    suspend fun deleteOrder(entity: OrderEntity): Int

    @Query("DELETE FROM ${OrderEntity.TABLE_NAME} WHERE 1 = 1")
    suspend fun deleteOrders(): Int
}