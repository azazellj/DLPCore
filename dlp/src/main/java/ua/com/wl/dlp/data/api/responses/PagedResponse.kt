package ua.com.wl.dlp.data.api.responses

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class PagedResponse<T>(
    @SerializedName("page_number")
    val page: Int? = null,

    @SerializedName(value = "count", alternate = ["per_page", "page_size"])
    val count: Int? = null,

    @SerializedName("total_pages_count")
    val pagesCount: Int? = null,

    @SerializedName("total_items_count")
    val itemsCount: Int? = null,

    @SerializedName("next")
    val nextPage: String? = null,

    @SerializedName("previous")
    val previousPage: String? = null,

    @SerializedName(value = "data", alternate = ["results"])
    val items: List<T> = listOf())