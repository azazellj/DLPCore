package ua.com.wl.dlp.domain.interactors.impl

import ua.com.wl.dlp.data.api.NewsApiV1
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.news.BaseArticleResponse
import ua.com.wl.dlp.data.api.responses.news.ArticleResponse
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.interactors.NewsFeedInteractor

/**
 * @author Denis Makovskyi
 */

class NewsFeedInteractorImpl(
    errorsMapper: ErrorsMapper,
    private val apiV1: NewsApiV1) : NewsFeedInteractor, UseCase(errorsMapper) {

    override suspend fun getCityNewsFeed(
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BaseArticleResponse>> =
        callApi(call = { apiV1.getCityNewsFeed(page, count) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun getShopNewsFeed(
        shopId: Int,
        page: Int?,
        count: Int?
    ): Result<PagedResponse<BaseArticleResponse>> =
        callApi(call = { apiV1.getShopNewsFeed(shopId, page, count) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }

    override suspend fun getNewsItem(id: Int): Result<ArticleResponse> =
        callApi(call = { apiV1.getNewsItem(id) }).flatMap { response ->
            response.ifPresentOrDefault(
                { Result.Success(it) },
                { Result.Failure(ApiException()) })
        }
}