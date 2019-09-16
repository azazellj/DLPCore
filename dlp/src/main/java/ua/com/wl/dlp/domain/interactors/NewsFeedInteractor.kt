package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.news.BaseNewsItemResponse
import ua.com.wl.dlp.data.api.responses.news.NewsItemResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface NewsFeedInteractor {

    suspend fun getCityNewsFeed(
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseNewsItemResponse>>

    suspend fun getShopNewsFeed(
        shopId: Int,
        page: Int? = null,
        count: Int? = null
    ): Result<PagedResponse<BaseNewsItemResponse>>

    suspend fun getNewsItem(id: Int): Result<NewsItemResponse>
}