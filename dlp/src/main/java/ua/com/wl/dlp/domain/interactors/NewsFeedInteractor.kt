package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.news.BaseArticleResponse
import ua.com.wl.dlp.data.api.responses.news.ArticleResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface NewsFeedInteractor {

    suspend fun getCityNewsFeed(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseArticleResponse>>

    suspend fun getShopNewsFeed(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseArticleResponse>>

    suspend fun getNewsItem(id: Int): Result<ArticleResponse>
}