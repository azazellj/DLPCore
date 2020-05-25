package ua.com.wl.dlp.domain.workers

import java.util.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import android.content.Context

import androidx.work.*

import org.koin.core.inject

import ua.com.wl.dlp.core.di.koin.DLPCoreComponent
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.utils.Failure
import ua.com.wl.dlp.utils.Success
import ua.com.wl.dlp.utils.isEmpty

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

        const val ERROR_KEY_WHEN_READ_ORDERS = "error_when_read_orders"
        const val ERROR_KEY_WHEN_LOAD_OFFERS = "error_when_load_offers"
        const val ERROR_KEY_WHEN_WRITE_IN_DB = "error_when_write_in_db"

        fun schedule(context: Context, shopId: Int): UUID {
            val request = OneTimeWorkRequestBuilder<ShopOffersSyncWork>()
                .addTag(TAG)
                .setInputData(
                    Data.Builder()
                        .putInt(INPUT_KEY_SHOP_ID, shopId)
                        .build())
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

        @Deprecated(
            message = "Use general function for androidx.work.Data",
            replaceWith = ReplaceWith(
                expression = "containsBool(key: String)",
                imports = ["ua.com.wl.dlp.utils.Extensions"])
        )
        fun containsError(key: String, data: Data): Boolean =
            data.keyValueMap.containsKey(key) && data.getBoolean(key, false)
    }

    private val shopId = inputData.getInt(INPUT_KEY_SHOP_ID, 0)
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
        shopInteractor.getPersistedOffers(shopId)
            .sOnSuccess { persistedOffers ->
                loadOffers(persistedOffers)
            }.onFailure {
                outputs.putBoolean(ERROR_KEY_WHEN_READ_ORDERS, true)
            }
    }

    private suspend fun loadOffers(persistedOffers: List<OfferEntity>) {
        for (persistedOffer in persistedOffers) {
            when(val offerResult = shopInteractor.getOffer(persistedOffer.id)) {
                is Success -> updatePersistedOffer(offerResult.data)
                is Failure -> outputs.putBoolean(ERROR_KEY_WHEN_LOAD_OFFERS, true)
            }
        }
        shopInteractor.populatePersistedOffersPrice(shopId)
    }

    private suspend fun updatePersistedOffer(offer: BaseOfferResponse) {
        shopInteractor.updatePersistedOffer(shopId, offer)
            .onFailure {
                outputs.putBoolean(ERROR_KEY_WHEN_WRITE_IN_DB, true)
            }
    }
}