package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import android.app.Application

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.NewsApiV1
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.BalanceChangeResponse
import ua.com.wl.dlp.data.api.responses.news.BaseArticleResponse
import ua.com.wl.dlp.data.api.responses.news.ArticleResponse
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.interactors.NewsFeedInteractor
import ua.com.wl.dlp.utils.processBalanceChanges
import ua.com.wl.dlp.utils.sendBroadcastMessage

/**
 * @author Denis Makovskyi
 */

class NewsFeedInteractorImpl constructor(
    errorsMapper: ErrorsMapper,
    private val app: Application,
    private val apiV1: NewsApiV1,
    private val consumerPreferences: ConsumerPreferences
) : UseCase(errorsMapper), NewsFeedInteractor {

    override suspend fun getCityNewsFeed(page: Int?, count: Int?): Result<PagedResponse<BaseArticleResponse>> =
        callApi(call = { apiV1.getCityNewsFeed(page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getShopNewsFeed(shopId: Int, page: Int?, count: Int?): Result<PagedResponse<BaseArticleResponse>> =
        callApi(call = { apiV1.getShopNewsFeed(shopId, page, count) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun getArticle(id: Int): Result<ArticleResponse> =
        callApi(call = { apiV1.getArticle(id) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }

    override suspend fun collectBonusesPerArticleView(id: Int): Result<BalanceChangeResponse> =
        callApi(call = { apiV1.collectBonusesPerView(id) })
            .flatMap { responseOpt ->
                responseOpt.ifPresentOrDefault(
                    { Result.Success(it) },
                    { Result.Failure(ApiException()) })
            }.sOnSuccess { changeResponse ->
                val change = changeResponse.change
                if (change != null) {
                    val changes = processBalanceChanges(Dispatchers.IO, changeResponse.change, consumerPreferences)
                    if (changes.isNotEmpty()) {
                        withContext(Dispatchers.Main.immediate) {
                            CoreBusEventsFactory.profileChanges(changes)
                            sendBroadcastMessage(app, Constants.RECEIVER_ACTION_SOUND_BONUSES)
                        }
                    }
                }
            }
}