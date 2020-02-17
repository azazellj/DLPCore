package ua.com.wl.dlp.data.db.datasources

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity

/**
 * @author Denis Makovskyi
 */

interface ShopsDataSource {

    suspend fun getShop(id: Int): Optional<ShopEntity>

    suspend fun insertShop(shop: ShopEntity): Boolean

    suspend fun updateShop(shop: ShopEntity): Boolean

    suspend fun deleteShop(shop: ShopEntity): Boolean

    suspend fun deleteShops(): Boolean

    suspend fun getOffer(id: Int): Optional<OfferEntity>

    suspend fun getOrder(id: Int, shopId: Int): Optional<OfferEntity>

    suspend fun getOrders(): List<ShopEntity>

    suspend fun getOrders(shopId: Int): List<OfferEntity>

    suspend fun insertOrder(offer: OfferEntity): Boolean

    suspend fun updateOrder(offer: OfferEntity): Boolean

    suspend fun deleteOrder(offer: OfferEntity): Boolean
}