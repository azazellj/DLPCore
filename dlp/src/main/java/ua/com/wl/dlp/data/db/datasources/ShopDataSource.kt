package ua.com.wl.dlp.data.db.datasources

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity

/**
 * @author Denis Makovskyi
 */

interface ShopDataSource {

    suspend fun getShop(id: Int): Optional<ShopEntity>

    suspend fun insertShop(shop: ShopEntity): Boolean

    suspend fun updateShop(shop: ShopEntity): Boolean

    suspend fun deleteShop(shop: ShopEntity): Boolean

    suspend fun deleteShops(): Boolean

    suspend fun getOffer(id: Int, shopId: Int): Optional<OfferEntity>

    suspend fun getOffers(shopId: Int): List<OfferEntity>

    suspend fun insertOffer(offer: OfferEntity): Boolean

    suspend fun updateOffer(offer: OfferEntity): Boolean

    suspend fun deleteOffer(offer: OfferEntity): Boolean
}