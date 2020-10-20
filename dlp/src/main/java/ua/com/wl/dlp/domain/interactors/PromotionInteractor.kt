package ua.com.wl.dlp.domain.interactors

import retrofit2.http.Path
import ua.com.wl.dlp.data.api.responses.CollectionResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.orders.order.BaseOrderResponse
import ua.com.wl.dlp.data.api.responses.promotion.PromotionsResponse
import ua.com.wl.dlp.domain.Result
import java.util.*

interface PromotionInteractor {

    suspend fun getPromotions(
        page: Int? = null,
        count: Int? = null,
        language: String = Locale.getDefault().language
    ): Result<PagedResponse<PromotionsResponse>>


    suspend fun getPromotionDetail(
        storeChainId: Int,
        language: String = Locale.getDefault().language
    ): Result<PromotionsResponse>
}