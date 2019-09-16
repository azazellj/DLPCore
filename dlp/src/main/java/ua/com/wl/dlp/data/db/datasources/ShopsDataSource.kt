package ua.com.wl.dlp.data.db.datasources

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity

/**
 * @author Denis Makovskyi
 */

interface ShopsDataSource {

    suspend fun getShop(id: Int): Optional<ShopEntity>

    suspend fun getShops(): List<ShopEntity>

    suspend fun upsertShop(shop: ShopEntity): Boolean

    suspend fun deleteShop(id: Int): Boolean

    suspend fun deleteShop(shop: ShopEntity): Boolean

    suspend fun deleteShops(): Boolean

    suspend fun getOffer(id: Int): Optional<OfferEntity>

    suspend fun getOffers(): List<OfferEntity>

    suspend fun getShopOffers(shopId: Int): List<OfferEntity>

    suspend fun upsertOffer(offer: OfferEntity): Boolean

    suspend fun deleteOffer(id: Int): Boolean

    suspend fun deleteOffer(offer: OfferEntity): Boolean

    suspend fun deleteOffers(): Boolean

    suspend fun deleteShopOffers(shopId: Int): Boolean
}