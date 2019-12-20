package ua.com.wl.dlp.data.db.datasources.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.db.DbErrorKeys
import ua.com.wl.dlp.data.db.dao.shops.OffersDao
import ua.com.wl.dlp.data.db.dao.shops.ShopsDao
import ua.com.wl.dlp.data.db.dao.shops.OrdersDao
import ua.com.wl.dlp.data.db.datasources.ShopDataSource
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.data.db.entities.shops.OrderEntity
import ua.com.wl.dlp.domain.exeptions.db.DbQueryException
import ua.com.wl.dlp.utils.isEqualsTo
import ua.com.wl.dlp.utils.isGreaterThan
import ua.com.wl.dlp.utils.only

/**
 * @author Denis Makovskyi
 */

class ShopDataSourceImpl constructor(
    private val shopsDao: ShopsDao,
    private val offersDao: OffersDao,
    private val ordersDao: OrdersDao
) : ShopDataSource {

    override suspend fun getShop(id: Int): Optional<ShopEntity> =
        try {
            withContext(Dispatchers.IO) {
                Optional.ofNullable(shopsDao.getShop(id))
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }

    override suspend fun insertShop(shop: ShopEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.insertShop(shop) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.INSERT_QUERY_ERROR, e)
        }

    override suspend fun updateShop(shop: ShopEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.updateShop(shop) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPDATE_QUERY_ERROR, e)
        }

    override suspend fun deleteShop(shop: ShopEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                for (offer in ordersDao.getOffers(shop.id)) {
                    val isOrderDeleted =
                        ordersDao.deleteOrder(OrderEntity(shop.id, offer.id)) isGreaterThan 0
                    val areOrdersEmpty = ordersDao.getCount(offer.id) isEqualsTo 0
                    if (isOrderDeleted && areOrdersEmpty) {
                        offersDao.deleteOffer(offer)
                    }
                }
                shopsDao.deleteShop(shop) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR, e)
        }

    override suspend fun deleteShops(): Boolean =
        withContext(Dispatchers.IO) {
            try {
                ordersDao.deleteOrders()
                offersDao.deleteOffers()
                shopsDao.deleteShops() isGreaterThan 0

            } catch (e: Exception) {
                throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR, e)
            }
        }

    override suspend fun getOffer(id: Int): Optional<OfferEntity> =
        try {
            withContext(Dispatchers.IO) {
                Optional.ofNullable(offersDao.getOffer(id))
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }

    override suspend fun getOrder(id: Int, shopId: Int): Optional<OfferEntity> =
        try {
            withContext(Dispatchers.IO) {
                ordersDao.getOrder(shopId, id)?.let { order ->
                    Optional.ofNullable(ordersDao.getOffer(order.shopId, order.offerId)?.apply {
                        this.shopId = order.shopId
                        this.preOrdersCount = order.preOrdersCount
                    })
                } ?: Optional.empty()
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }

    override suspend fun getOrders(): List<ShopEntity> =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.getShops().also { shops ->
                    for (shop in shops) {
                        shop.offers = ordersDao.getOffers(shop.id)
                    }
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }

    override suspend fun getOrders(shopId: Int): List<OfferEntity> =
        try {
            withContext(Dispatchers.IO) {
                ordersDao.getOffers(shopId).also { offers ->
                    for (offer in offers) {
                        ordersDao.getOrder(shopId, offer.id)?.only { order ->
                            offer.shopId = order.shopId
                            offer.preOrdersCount = order.preOrdersCount
                        } ?: throw IllegalStateException("Order was not found")
                    }
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }

    override suspend fun insertOrder(offer: OfferEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                val isOfferInserted = if (offersDao.getCount(offer.id) isGreaterThan 0) {
                    offersDao.updateOffer(offer) isGreaterThan 0
                } else {
                    offersDao.insertOffer(offer) isGreaterThan 0
                }
                OrderEntity(offer.shopId, offer.id, offer.preOrdersCount).let {
                    isOfferInserted && ordersDao.insertOrder(it) isGreaterThan 0
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.INSERT_QUERY_ERROR, e)
        }

    override suspend fun updateOrder(offer: OfferEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                OrderEntity(offer.shopId, offer.id, offer.preOrdersCount).let {
                    offersDao.updateOffer(offer) isGreaterThan 0
                            && ordersDao.updateOrder(it) isGreaterThan 0
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPDATE_QUERY_ERROR, e)
        }

    override suspend fun deleteOrder(offer: OfferEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                var isOrderDeleted =
                    ordersDao.deleteOrder(OrderEntity(offer.shopId, offer.id)) isGreaterThan 0
                val areOrdersEmpty = ordersDao.getCount(offer.id) isEqualsTo 0
                if (isOrderDeleted && areOrdersEmpty) {
                    isOrderDeleted = offersDao.deleteOffer(offer) isGreaterThan 0
                }
                isOrderDeleted
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR, e)
        }
}