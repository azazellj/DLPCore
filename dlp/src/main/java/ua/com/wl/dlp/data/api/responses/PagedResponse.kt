package ua.com.wl.dlp.data.api.responses

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PagedResponse<T>(
    @SerializedName("page_number")
    val page: Int,
    @SerializedName(value = "per_page", alternate = ["count"])
    val count: Int,
    @SerializedName("total_pages_count")
    val pagesCount: Int,
    @SerializedName("total_items_count")
    val itemsCount: Int,
    @SerializedName("next")
    val nextPage: String?,
    @SerializedName("previous")
    val previousPage: String?,
    @SerializedName(value = "data", alternate = ["results"])
    val items: List<T>?)