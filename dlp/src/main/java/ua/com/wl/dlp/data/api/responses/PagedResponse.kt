package ua.com.wl.dlp.data.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class PagedResponse<T>(
    @Json(name = "page_number")
    open val page: Int?,

    @Json(name = "total_pages_count")
    open val pagesCount: Int?,

    @Json(name = "total_items_count")
    open val itemsCount: Int?,

    @Json(name = "next")
    open val nextPage: String?,

    @Json(name = "previous")
    open val previousPage: String?,

    /**
     * merged fields for items.
     */
    @Json(name = "data")
    open val data: List<T> = listOf(),
    @Json(name = "results")
    open val results: List<T> = listOf(),

    /**
     * merged fields for count.
     */
    @Json(name = "count")
    open val countNumber: Int?,
    @Json(name = "per_page")
    open val perPage: Int?,
    @Json(name = "page_size")
    open val pageSize: Int?
) {
    val items: List<T>
        get() {
            return if (data.isNotEmpty()) data else results
        }
    val count: Int
        get() {
            return countNumber ?: perPage ?: pageSize ?: 0
        }
}