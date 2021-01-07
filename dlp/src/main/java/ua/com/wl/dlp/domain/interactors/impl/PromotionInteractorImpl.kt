package ua.com.wl.dlp.domain.interactors.impl

import ua.com.wl.dlp.data.api.PromotionApiV2
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.promotion.PromotionsResponse
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.interactors.PromotionInteractor
import ua.com.wl.dlp.utils.fromDataResponse
import ua.com.wl.dlp.utils.fromResponse

class PromotionInteractorImpl(
    errorsMapper: ErrorsMapper,
    private val apiV2: PromotionApiV2,
) : UseCase(errorsMapper), PromotionInteractor {

    override suspend fun getPromotions(
        page: Int?,
        count: Int?,
        language: String
    ): Result<PagedResponse<PromotionsResponse>> {
        return callApi(call = { apiV2.getPromotions(page, count, language) })
            .fromDataResponse()
    }

    override suspend fun getPromotionDetail(
        storeChainId: Int,
        language: String
    ): Result<PromotionsResponse> {
        return callApi(call = { apiV2.getPromotionDetail(storeChainId, language) })
            .fromResponse()
    }
}