package ua.com.wl.dlp.domain.workers

import java.util.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import android.content.Context

import androidx.work.*

import org.koin.core.inject

import ua.com.wl.dlp.core.DLPCoreComponent
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.utils.Failure
import ua.com.wl.dlp.utils.Success
import ua.com.wl.dlp.utils.isEmpty

/**
 * @author Denis Makovskyi
 */

class ShopsOffersSyncWork(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters), DLPCoreComponent {

    companion object {

        val TAG = ShopsOffersSyncWork::class.java.name

        const val ERROR_KEY_WHEN_READ_ORDERS = "error_when_read_orders"
        const val ERROR_KEY_WHEN_LOAD_OFFERS = "error_when_load_offers"
        const val ERROR_KEY_WHEN_WRITE_IN_DB = "error_when_write_in_db"

        fun schedule(context: Context, shopId: Int): UUID {
            val request = OneTimeWorkRequestBuilder<ShopOffersSyncWork>()
                .addTag(TAG)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build())
                .build()
            WorkManager.getInstance(context)
                .beginUniqueWork(TAG, ExistingWorkPolicy.REPLACE, request)
                .enqueue()
            return request.id
        }
    }

    private val shopInteractor: ShopInteractor by inject()

    private val outputs: Data.Builder = Data.Builder()

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            launch()
        }
        val data = outputs.build()
        return if (data.isEmpty()) {
            Result.success()
        } else {
            Result.failure(data)
        }
    }

    private suspend fun launch() {
        shopInteractor.getPersistedOffers()
            .sOnSuccess { shops ->
                for (shop in shops) {
                    loadOffers(shop.id, shop.offers)
                }
            }.onFailure {
                outputs.putBoolean(ERROR_KEY_WHEN_READ_ORDERS, true)
            }
    }

    private suspend fun loadOffers(shopId: Int, persistedOffers: List<OfferEntity>) {
        for (persistedOffer in persistedOffers) {
            when(val offerResult = shopInteractor.getOffer(persistedOffer.id)) {
                is Success -> updatePersistedOffer(shopId, offerResult.data)
                is Failure -> outputs.putBoolean(ERROR_KEY_WHEN_LOAD_OFFERS, true)
            }
        }
        shopInteractor.populatePersistedOffersPrice(shopId)
    }

    private suspend fun updatePersistedOffer(shopId: Int, offer: BaseOfferResponse) {
        shopInteractor.updatePersistedOffer(shopId, offer)
            .onFailure {
                outputs.putBoolean(ERROR_KEY_WHEN_WRITE_IN_DB, true)
            }
    }
}