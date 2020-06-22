package ua.com.wl.dlp.data.db.datasources.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.archetype.utils.Optional

import ua.com.wl.dlp.data.db.DbErrorKeys
import ua.com.wl.dlp.data.db.dao.shops.ShopsDao
import ua.com.wl.dlp.data.db.dao.shops.OffersDao
import ua.com.wl.dlp.data.db.dao.shops.OrdersDao
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.OrderEntity
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.domain.exeptions.db.DbQueryException
import ua.com.wl.dlp.utils.isEqualsTo
import ua.com.wl.dlp.utils.isGreaterThan

/**
 * @author Denis Makovskyi
 */

class ShopsDataSourceImpl(
    private val shopsDao: ShopsDao,
    private val offersDao: OffersDao,
    private val ordersDao: OrdersDao
) : ShopsDataSource {

    override suspend fun getShop(id: Int): Optional<ShopEntity> {
        return try {
            withContext(Dispatchers.IO) {
                Optional.ofNullable(shopsDao.getShop(id))
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }
    }

    override suspend fun insertShop(shop: ShopEntity): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                shopsDao.insertShop(shop) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.INSERT_QUERY_ERROR, e)
        }
    }

    override suspend fun updateShop(shop: ShopEntity): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                shopsDao.updateShop(shop) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPDATE_QUERY_ERROR, e)
        }
    }

    override suspend fun deleteShop(shop: ShopEntity): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                for (offer in ordersDao.getOffers(shop.id)) {
                    val isOrderDeleted = ordersDao.deleteOrder(OrderEntity(shop.id, offer.id)) isGreaterThan 0
                    val isRelationNotExists = ordersDao.getCount(offer.id) isEqualsTo 0
                    if (isOrderDeleted && isRelationNotExists) {
                        offersDao.deleteOffer(offer)
                    }
                }
                shopsDao.deleteShop(shop) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR, e)
        }
    }

    override suspend fun deleteShops(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                ordersDao.deleteOrders()
                offersDao.deleteOffers()
                shopsDao.deleteShops() isGreaterThan 0

            } catch (e: Exception) {
                throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR, e)
            }
        }
    }

    override suspend fun getOffer(id: Int): Optional<OfferEntity> {
        return try {
            withContext(Dispatchers.IO) {
                Optional.ofNullable(offersDao.getOffer(id))
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }
    }

    override suspend fun getOrder(offerId: Int, shopId: Int): Optional<OfferEntity> {
        return try {
            withContext(Dispatchers.IO) {
                ordersDao.getOrder(shopId, offerId)?.let { order ->
                    Optional.ofNullable(ordersDao.getOffer(order.shopId, order.offerId)?.also { offer ->
                        offer.shopId = order.shopId
                        offer.preOrdersCount = order.preOrdersCount
                    })
                } ?: Optional.empty()
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }
    }

    override suspend fun getOrders(): List<ShopEntity> {
        return try {
            withContext(Dispatchers.IO) {
                shopsDao.getShops().also { shops ->
                    for (shop in shops) {
                        shop.offers = ordersDao.getOffers(shop.id)
                            .map { offer ->
                                offer.apply {
                                    ordersDao.getOrder(shop.id, offer.id)?.let { order ->
                                        this.shopId = order.shopId
                                        this.preOrdersCount = order.preOrdersCount
                                    }
                                }
                            }
                    }
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }
    }

    override suspend fun getOrders(shopId: Int): List<OfferEntity> {
        return try {
            withContext(Dispatchers.IO) {
                ordersDao.getOffers(shopId).also { offers ->
                    for (offer in offers) {
                        ordersDao.getOrder(shopId, offer.id)?.let { order ->
                            offer.shopId = order.shopId
                            offer.preOrdersCount = order.preOrdersCount
                        }
                    }
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR, e)
        }
    }

    override suspend fun insertOrder(offer: OfferEntity): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val isOfferInserted = if (offersDao.getCount(offer.id) isGreaterThan 0) {
                    offersDao.updateOffer(offer) isGreaterThan 0
                } else {
                    offersDao.insertOffer(offer) isGreaterThan 0
                }
                val order = OrderEntity(
                    offer.shopId, offer.id, offer.preOrdersCount)
                isOfferInserted && ordersDao.insertOrder(order) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.INSERT_QUERY_ERROR, e)
        }
    }

    override suspend fun updateOrder(offer: OfferEntity): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val order = OrderEntity(
                    offer.shopId, offer.id, offer.preOrdersCount)
                offersDao.updateOffer(offer) isGreaterThan 0
                        && ordersDao.updateOrder(order) isGreaterThan 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPDATE_QUERY_ERROR, e)
        }
    }

    override suspend fun deleteOrder(offer: OfferEntity): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val order = OrderEntity(offer.shopId, offer.id)
                val isOrderDeleted = ordersDao.deleteOrder(order) isGreaterThan 0
                val isRelationNotExists = ordersDao.getCount(offer.id) isEqualsTo 0
                if (isOrderDeleted && isRelationNotExists) {
                    offersDao.deleteOffer(offer) isGreaterThan 0
                } else false
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR, e)
        }
    }
}