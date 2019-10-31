package ua.com.wl.dlp.domain.interactors

import java.util.*

import ua.com.wl.archetype.utils.Optional

import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.shop.CityShopsResponse
import ua.com.wl.dlp.data.api.responses.shop.ShopResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.shop.rubric.RubricResponse
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.exeptions.db.DbQueryException

/**
 * @author Denis Makovskyi
 */

interface ShopInteractor : OffersInteractor {

    /**
     * Allows to load all available cities with shops.
     * @param [page] page number.
     * @param [count] items count per page.
     * @return if success, result is an instance of [Result.Success] with [PagedResponse] with [CityShopsResponse] inside,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getCityShops(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<CityShopsResponse>>

    /**
     * Allows to load shop's detail information.
     * @param [shopId] shop's unique identifier.
     * @return if success, result is an instance of [Result.Success] with [ShopResponse] inside,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getShop(shopId: Int): Result<ShopResponse>

    /**
     * Allows to load shop's offers rubrics.
     * @param [shopId] shop's unique identifier.
     * @param [language] localization for rubrics names.
     * @return if success, result is an instance of [Result.Success] with [CollectionResponse] with [RubricResponse] inside,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getRubrics(
        shopId: Int,
        language: String = Locale.getDefault().language
    ): Result<CollectionResponse<RubricResponse>>

    /**
     * Allows to load list of shop's offers by given rubric.
     * @param [shopId] shop's unique identifier.
     * @param [page] page number.
     * @param [count] items count per page.
     * @param [rubricId] rubric's unique identifier.
     * @return if success, result is an instance of [Result.Success] with [PagedResponse] with [BaseOfferResponse] inside,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null,
        rubricId: String? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    /**
     * Allows to load list of shop's promo offers.
     * @param [shopId] shop's unique identifier.
     * @param [page] page number.
     * @param [count] items count per page.
     * @return if success, result is an instance of [Result.Success] with [PagedResponse] with [BaseOfferResponse] inside,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getPromoOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    /**
     * Allows to load list of shop's novelty offers.
     * @param [shopId] shop's unique identifier.
     * @param [page] page number.
     * @param [count] items count per page.
     * @return if success, result is an instance of [Result.Success] with [PagedResponse] with [BaseOfferResponse] inside,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getNoveltyOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    /**
     * Allows to load list of offers which consumer has been added in favourites for given shop.
     * @param [shopId] shop's unique identifier.
     * @param [page] page number.
     * @param [count] items count per page.
     * @return if success, result is an instance of [Result.Success] with [PagedResponse] with [BaseOfferResponse] inside,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getFavouriteOffers(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseOfferResponse>>

    /**
     * Allows to persist shop in local DB.
     * @param [shop] entity that will be persisted.
     * @return If success, result is an instance of [Result.Success] with boolean flag inside, that indicates
     * whether entity was saved successfully, otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun persistShop(shop: ShopEntity): Result<Boolean>

    /**
     * Allows to retrieve persisted shop entity from local DB by given id.
     * @param [shopId] shop's unique identifier.
     * @return If success, result is an instance of [Result.Success] with [Optional] wrapper, that may
     * contain requested entity, otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getPersistedShop(shopId: Int): Result<Optional<ShopEntity>>

    /**
     * Allows to retrieve ll persisted shop entities from local DB.
     * @return If success, result is an instance of [Result.Success] with [ShopEntity] list,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getPersistedShops(): Result<List<ShopEntity>>

    /**
     * Allows to Delete persisted shop from local DB.
     * @param [shop] entity that will be deleted.
     * @return If success, result is an instance of [Result.Success] with boolean flag inside, that indicates
     * whether entity was deleted successfully, otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun deletePersistedShop(shop: ShopEntity): Result<Boolean>

    /**
     * Allows to update persisted offer in local DB. [BaseOfferResponse] is a projection of [OfferEntity].
     * @param [offer] object that will update related [OfferEntity].
     * @return If success, result is an instance of [Result.Success] with boolean flag inside, that indicates
     * whether entity was updated successfully, otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun updatePersistedOffer(offer: BaseOfferResponse): Result<Boolean>

    /**
     * Takes persisted offer by given id from local DB and increment pre orders counter if entity exist.
     * @param [offerId] - [OfferEntity]'s unique identifier.
     * @return If success, result is an instance of [Result.Success] with [OfferEntity] inside,
     * which cache related [BaseOfferResponse] info and holds actual pre orders counter,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun incrementPersistedPreOrderCounter(offerId: Int): Result<OfferEntity>

    /**
     * Allow to create new or update existing offer in local DB and initialize or increment pre orders counter.
     * @param [shopId] - [ShopEntity]'s identifier to which belongs [OfferEntity].
     * @param [offer] - projection of [OfferEntity] on which it will be created or updated.
     * @return If success, result is an instance of [Result.Success] with [OfferEntity] inside,
     * which cache related [BaseOfferResponse] info and holds actual pre orders counter,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun incrementPersistedPreOrderCounter(shopId: Int, offer: BaseOfferResponse): Result<OfferEntity>

    /**
     * Takes existing offer by given id from local DB and decrement pre orders counter or delete entity if counter
     * after decrementing is equals to zero.
     * @param [shopId] - [ShopEntity]'s identifier to which belongs [OfferEntity],
     * needs for sending broadcast event with total price of all persisted offers by given shop's identifier
     * through [ua.com.wl.archetype.core.android.bus.Bus].
     * @param [offerId] - [OfferEntity]'s unique identifier.
     * @param [tradeItem] - [OfferEntity]'s trade identifier, need for sending broadcast event with persisted offer's
     * pre orders counter through [ua.com.wl.archetype.core.android.bus.Bus].
     * @return If success, result is either an instance of [Result.Success] with [OfferEntity] inside,
     * which cache related [BaseOfferResponse] info and holds actual pre orders counter,
     * or [Result.Failure] with [DbQueryException] inside that has [ua.com.wl.dlp.data.db.DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE] error key as a message value,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun decrementPersistedPreOrderCounter(shopId: Int, offerId: Int, tradeItem: Int): Result<OfferEntity>

    /**
     * Allows to update and decrement pre orders counter or delete persisted offer from local DB.
     * @param [shopId] - [ShopEntity]'s identifier to which belongs [OfferEntity].
     * @param [offer] - projection of [OfferEntity] on which it will be updated or deleted.
     * @return If success, result is either an instance of [Result.Success] with [OfferEntity] inside,
     * which cache related [BaseOfferResponse] info and holds actual pre orders counter,
     * or [Result.Failure] with [DbQueryException] inside that has [ua.com.wl.dlp.data.db.DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE] error key as a message value,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun decrementPersistedPreOrderCounter(shopId: Int, offer: BaseOfferResponse): Result<OfferEntity>

    /**
     * Takes all persisted [OfferEntity] for [ShopEntity] by given shop id and calculate total price off
     * all persisted offers, after that send broadcast event in [ua.com.wl.archetype.core.android.bus.Bus]
     * @param [shopId] shop's unique identifier.
     */
    suspend fun populatePersistedPreOrdersPrice(shopId: Int)

    /**
     * Allows to retrieve persisted offer from local DB.
     * @param [offerId] offer's unique identifier.
     * @return If success, result is an instance of [Result.Success] with [Optional] wrapper, that may
     * contains requested entity, otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getPersistedOffer(offerId: Int): Result<Optional<OfferEntity>>

    /**
     * Allows to retrieveall persisted offers from local DB by given shop id.
     * @param [shopId] shop's unique identifier.
     * @return If success, result is an instance of [Result.Success] with [OfferEntity] list,
     * otherwise [Result.Failure] with detailed [Throwable].
     */
    suspend fun getPersistedOffers(shopId: Int): Result<List<OfferEntity>>
}