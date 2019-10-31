package ua.com.wl.dlp.domain.workers

import java.util.*

import android.content.Context

import androidx.work.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import org.koin.core.inject

import ua.com.wl.dlp.core.DLPCoreComponent
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.utils.Failure
import ua.com.wl.dlp.utils.Success
import ua.com.wl.dlp.utils.add

/**
 * @author Denis Makovskyi
 */

class ShopOffersSyncWork(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), DLPCoreComponent {

    companion object {

        val TAG = ShopOffersSyncWork::class.java.name

        const val INPUT_KEY_SHOP_ID = "shop_id"

        const val OUTPUT_KEY_ERROR_WHEN = "error_when"
        const val OUTPUT_KEY_SHOP_IDS = "shop_ids"
        const val OUTPUT_KEY_OFFER_IDS = "offer_ids"
        const val OUTPUT_KEY_ORDER_IDS = "order_ids"

        const val WHEN_READING_SHOP = "when_reading_shop"
        const val WHEN_READING_PRE_ORDERS = "when_reading_pre_orders"
        const val WHEN_LOADING_REMOTE_OFFER = "when_loading_remote_offer"
        const val WHEN_SYNCING_OFFER_WITH_ORDER = "when_syncing_offer_with_order"

        fun schedule(context: Context, shopId: Int): UUID {
            val request = OneTimeWorkRequestBuilder<ShopOffersSyncWork>()
                .apply {
                    addTag(TAG)
                    setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build())
                    setInputData(
                        Data.Builder()
                            .putInt(INPUT_KEY_SHOP_ID, shopId)
                            .build())
                }.build()
            WorkManager.getInstance(context)
                .beginUniqueWork(TAG, ExistingWorkPolicy.REPLACE, request)
                .enqueue()
            return request.id
        }
    }

    private val shopId = inputData.getInt(INPUT_KEY_SHOP_ID, 0)
    private val shopInteractor: ShopInteractor by inject()

    private var outputs: Data.Builder = Data.Builder()

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            launch()
        }
        val data = outputs.build()
        return if (data.keyValueMap.isEmpty()) {
            Result.success()
        } else {
            Result.failure(data)
        }
    }

    private suspend fun launch() {
        when(val shopResult = shopInteractor.getPersistedShop(shopId)) {
            is Success -> {
                shopResult.data.sIfPresentOrElse(
                    { getPreOrders(it) },
                    { outputs.putString(OUTPUT_KEY_ERROR_WHEN, WHEN_READING_SHOP) }
                )
            }
            is Failure -> {
                outputs.putString(OUTPUT_KEY_ERROR_WHEN, WHEN_READING_SHOP)
            }
        }
    }

    private suspend fun getPreOrders(shop: ShopEntity) {
        when(val preOrdersResult = shopInteractor.getPersistedOffers(shop.id)) {
            is Success -> getOffers(preOrdersResult.data)
            is Failure -> {
                outputs.apply {
                    putString(OUTPUT_KEY_ERROR_WHEN, WHEN_READING_PRE_ORDERS)
                    putIntArray(OUTPUT_KEY_SHOP_IDS, build().getIntArray(OUTPUT_KEY_SHOP_IDS).add(shop.id))
                }
            }
        }
    }

    private suspend fun getOffers(preOrders: List<OfferEntity>) {
        for (preOrder in preOrders) {
            when(val offerResult = shopInteractor.getOffer(preOrder.id)) {
                is Success -> updatePreOrder(offerResult.data)
                is Failure -> {
                    outputs.apply {
                        putString(OUTPUT_KEY_ERROR_WHEN, WHEN_LOADING_REMOTE_OFFER)
                        putIntArray(OUTPUT_KEY_OFFER_IDS, build().getIntArray(OUTPUT_KEY_SHOP_IDS).add(preOrder.id))
                    }
                }
            }
        }
        shopInteractor.populatePersistedPreOrdersPrice(shopId)
    }

    private suspend fun updatePreOrder(offer: BaseOfferResponse) {
        val upsertResult = shopInteractor.updatePersistedOffer(offer)
        if (upsertResult is Failure) {
            outputs.apply {
                putString(OUTPUT_KEY_ERROR_WHEN, WHEN_SYNCING_OFFER_WITH_ORDER)
                putIntArray(OUTPUT_KEY_ORDER_IDS, build().getIntArray(OUTPUT_KEY_ORDER_IDS).add(offer.id))
            }
        }
    }
}