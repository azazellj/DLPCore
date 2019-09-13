package ua.com.wl.dlp.data.db.datasources.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import androidx.lifecycle.LiveData

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.db.DbErrorKeys
import ua.com.wl.dlp.data.db.dao.orders.OffersDao
import ua.com.wl.dlp.data.db.dao.orders.ShopsDao
import ua.com.wl.dlp.data.db.datasources.OrdersDataSource
import ua.com.wl.dlp.data.db.entities.orders.OfferEntity
import ua.com.wl.dlp.data.db.entities.orders.ShopEntity
import ua.com.wl.dlp.domain.exeptions.db.DbQueryException

/**
 * @author Denis Makovskyi
 */

class OrdersDataSourceImpl(
    private val shopsDao: ShopsDao,
    private val offersDao: OffersDao) : OrdersDataSource {

    override suspend fun getShop(id: Int): Optional<ShopEntity> =
        try {
            withContext(Dispatchers.IO) {
                Optional.ofNullable(shopsDao.getShop(id))
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun getShops(): LiveData<List<ShopEntity>> =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.getShops()
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun upsertShop(shop: ShopEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.upsertShop(shop) > -1
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPSERT_QUERY_ERROR)
        }

    override suspend fun deleteShop(id: Int): Boolean =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.deleteShop(id) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }

    override suspend fun deleteShop(shop: ShopEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.deleteShop(shop) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }

    override suspend fun deleteShops(): Boolean =
        withContext(Dispatchers.IO) {
            try {
                shopsDao.deleteShops() > 0

            } catch (e: Exception) {
                throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
            }
        }

    override suspend fun getOffer(id: Int): Optional<OfferEntity> =
        try {
            withContext(Dispatchers.IO) {
                Optional.ofNullable(offersDao.getOffer(id))
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun getOffers(): LiveData<List<OfferEntity>> =
        try {
            withContext(Dispatchers.IO) {
                offersDao.getOffers()
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun getShopOffers(shopId: Int): LiveData<List<OfferEntity>> =
        try {
            withContext(Dispatchers.IO) {
                offersDao.getShopOffers(shopId)
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun upsertOffer(offer: OfferEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                offersDao.upsertOffer(offer) > -1
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPSERT_QUERY_ERROR)
        }

    override suspend fun deleteOffer(id: Int): Boolean =
        try {
            withContext(Dispatchers.IO) {
                offersDao.deleteOffer(id) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }

    override suspend fun deleteOffer(offer: OfferEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                offersDao.deleteOffer(offer) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }

    override suspend fun deleteOffers(): Boolean =
        try {
            withContext(Dispatchers.IO) {
                offersDao.deleteOffers() > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }

    override suspend fun deleteShopOffers(shopId: Int): Boolean =
        try {
            withContext(Dispatchers.IO) {
                offersDao.deleteShopOffers(shopId) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }
}