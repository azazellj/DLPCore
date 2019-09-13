package ua.com.wl.dlp.data.db.datasources

import androidx.lifecycle.LiveData

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.db.entities.orders.OfferEntity
import ua.com.wl.dlp.data.db.entities.orders.ShopEntity

/**
 * @author Denis Makovskyi
 */

interface OrdersDataSource {

    suspend fun getShop(id: Int): Optional<ShopEntity>

    suspend fun getShops(): LiveData<List<ShopEntity>>

    suspend fun upsertShop(shop: ShopEntity): Boolean

    suspend fun deleteShop(id: Int): Boolean

    suspend fun deleteShop(shop: ShopEntity): Boolean

    suspend fun deleteShops(): Boolean

    suspend fun getOffer(id: Int): Optional<OfferEntity>

    suspend fun getOffers(): LiveData<List<OfferEntity>>

    suspend fun getShopOffers(shopId: Int): LiveData<List<OfferEntity>>

    suspend fun upsertOffer(offer: OfferEntity): Boolean

    suspend fun deleteOffer(id: Int): Boolean

    suspend fun deleteOffer(offer: OfferEntity): Boolean

    suspend fun deleteOffers(): Boolean

    suspend fun deleteShopOffers(shopId: Int): Boolean
}