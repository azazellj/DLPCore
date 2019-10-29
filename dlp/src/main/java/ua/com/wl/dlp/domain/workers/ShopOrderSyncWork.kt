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

class ShopOrderSyncWork(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), DLPCoreComponent {

    companion object {

        const val INPUT_KEY_SHOP_ID = "order_id"
        const val INPUT_KEY_ORDER_ID = "order_id"

        fun schedule(
            context: Context,
            shopId: Int,
            orderId: Int,
            workerTag: String? = null): UUID {
            val uniqueWorkName = workerTag ?: orderId.toString()
            val request = OneTimeWorkRequestBuilder<ShopOrderSyncWork>()
                .apply {
                    addTag(uniqueWorkName)
                    setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build())
                    setInputData(
                        Data.Builder()
                            .putInt(INPUT_KEY_SHOP_ID, shopId)
                            .putInt(INPUT_KEY_ORDER_ID, orderId)
                            .build())
                }.build()
            WorkManager.getInstance(context)
                .beginUniqueWork(uniqueWorkName, ExistingWorkPolicy.REPLACE, request)
                .enqueue()
            return request.id
        }
    }

    private val shopId = inputData.getInt(INPUT_KEY_SHOP_ID, 0)
    private val orderId = inputData.getInt(INPUT_KEY_ORDER_ID, 0)
    private val shopInteractor: ShopInteractor by inject()

    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) {
            launch()
        }

    private suspend fun launch(): Result =
        when(val offerResult = shopInteractor.getOffer(orderId)) {
            is Success -> when(shopInteractor.updatePersistedPreOrder(offerResult.data)) {
                is Success -> {
                    shopInteractor.populatePersistedPreOrdersPrice(shopId)
                    Result.success()
                }
                is Failure -> Result.failure()
            }
            is Failure -> Result.failure()
        }
}