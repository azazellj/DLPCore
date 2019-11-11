package ua.com.wl.dlp.data.db.datasources.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.db.DbErrorKeys
import ua.com.wl.dlp.data.db.dao.shops.OffersDao
import ua.com.wl.dlp.data.db.dao.shops.ShopsDao
import ua.com.wl.dlp.data.db.dao.shops.ShopsOffersDao
import ua.com.wl.dlp.data.db.datasources.ShopDataSource
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopOfferEntity
import ua.com.wl.dlp.domain.exeptions.db.DbQueryException

/**
 * @author Denis Makovskyi
 */

class ShopDataSourceImpl(
    private val shopsDao: ShopsDao,
    private val offersDao: OffersDao,
    private val shopsOffersDao: ShopsOffersDao
) : ShopDataSource {

    override suspend fun getShop(id: Int): Optional<ShopEntity> =
        try {
            withContext(Dispatchers.IO) {
                Optional.ofNullable(shopsDao.getShop(id))
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun getShops(): List<ShopEntity> =
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
                shopsDao.upsertShop(shop) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPSERT_QUERY_ERROR)
        }

    override suspend fun updateShop(shop: ShopEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                shopsDao.updateShop(shop) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPDATE_QUERY_ERROR)
        }

    override suspend fun deleteShop(shop: ShopEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                for (offer in shopsOffersDao.getOffersForShop(shop.id)) {
                    val isRelationDeleted = shopsOffersDao.deleteRelation(shop.id, offer.id) > 0
                    if (isRelationDeleted && shopsOffersDao.getOfferEntries(offer.id) == 0) {
                        offersDao.deleteOffer(offer)
                    }
                }
                shopsDao.deleteShop(shop) > 0
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }

    override suspend fun deleteShops(): Boolean =
        withContext(Dispatchers.IO) {
            try {
                shopsOffersDao.deleteRelations()
                offersDao.deleteOffers()
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

    override suspend fun getOffer(id: Int, shopId: Int): Optional<OfferEntity> =
        try {
            withContext(Dispatchers.IO) {
                shopsOffersDao.getRelation(shopId, id)?.let { relation ->
                    Optional.ofNullable(shopsOffersDao.getOfferForShop(shopId, id)?.apply {
                        this.shopId = relation.shopId
                        this.preOrdersCount = relation.preOrdersCount
                    })
                } ?: Optional.empty()
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun getOffers(shopId: Int): List<OfferEntity> =
        try {
            withContext(Dispatchers.IO) {
                shopsOffersDao.getOffersForShop(shopId).also { offers ->
                    for (offer in offers) {
                        shopsOffersDao.getRelation(shopId, offer.id)?.let { relation ->
                            offer.shopId = relation.shopId
                            offer.preOrdersCount = relation.preOrdersCount
                        } ?: throw DbQueryException(DbErrorKeys.ENTITY_IS_NOT_EXISTS)
                    }
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.SELECT_QUERY_ERROR)
        }

    override suspend fun upsertOffer(offer: OfferEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                ShopOfferEntity(offer.shopId, offer.id, offer.preOrdersCount).let {
                    offersDao.upsertOffer(offer) > 0 && shopsOffersDao.upsertRelation(it) > 0
                }
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.UPSERT_QUERY_ERROR)
        }

    override suspend fun deleteOffer(offer: OfferEntity): Boolean =
        try {
            withContext(Dispatchers.IO) {
                var isDeleted = shopsOffersDao.deleteRelation(offer.shopId, offer.id) > 0
                if (isDeleted && (shopsOffersDao.getOfferEntries(offer.id) == 0)) {
                    isDeleted = offersDao.deleteOffer(offer) > 0
                }
                isDeleted
            }

        } catch (e: Exception) {
            throw DbQueryException(DbErrorKeys.DELETE_QUERY_ERROR)
        }
}