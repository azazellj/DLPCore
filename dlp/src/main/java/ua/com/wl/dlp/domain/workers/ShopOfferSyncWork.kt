package ua.com.wl.dlp.domain.workers

import java.util.*

import android.content.Context

import androidx.work.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import org.koin.core.inject

import ua.com.wl.dlp.core.DLPCoreComponent
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.utils.Failure
import ua.com.wl.dlp.utils.Success

/**
 * @author Denis Makovskyi
 */

class ShopOfferSyncWork(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), DLPCoreComponent {

    companion object {

        const val INPUT_KEY_SHOP_ID = "shop_id"
        const val INPUT_KEY_OFFER_ID = "offer_id"

        fun schedule(
            context: Context,
            shopId: Int,
            offerId: Int,
            workerTag: String? = null
        ): UUID {
            val uniqueWorkName = workerTag ?: offerId.toString()
            val request = OneTimeWorkRequestBuilder<ShopOfferSyncWork>()
                .apply {
                    addTag(uniqueWorkName)
                    setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build())
                    setInputData(
                        Data.Builder()
                            .putInt(INPUT_KEY_SHOP_ID, shopId)
                            .putInt(INPUT_KEY_OFFER_ID, offerId)
                            .build())
                }.build()
            WorkManager.getInstance(context)
                .beginUniqueWork(uniqueWorkName, ExistingWorkPolicy.REPLACE, request)
                .enqueue()
            return request.id
        }
    }

    private val shopId = inputData.getInt(INPUT_KEY_SHOP_ID, 0)
    private val offerId = inputData.getInt(INPUT_KEY_OFFER_ID, 0)
    private val shopInteractor: ShopInteractor by inject()

    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) {
            launch(
                Data.Builder()
                    .putInt(INPUT_KEY_OFFER_ID, offerId)
                    .build()
            )
        }

    private suspend fun launch(outputs: Data): Result =
        when(val offerResult = shopInteractor.getOffer(offerId)) {
            is Success -> {
                when(shopInteractor.updatePersistedOffer(offerResult.data)) {
                    is Success -> {
                        shopInteractor.populatePersistedPreOrdersPrice(shopId)
                        Result.success(outputs)
                    }
                    is Failure -> Result.failure(outputs)
                }
            }
            is Failure -> Result.failure(outputs)
        }
}