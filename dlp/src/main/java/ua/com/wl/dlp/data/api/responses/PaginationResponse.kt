package ua.com.wl.dlp.data.api.responses

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PaginationResponse<T>(
    @SerializedName("page_number")
    val pageNumber: Int,
    @SerializedName("per_page")
    val itemsPerPage: Int,
    @SerializedName("total_pages_count")
    val totalPagesCount: Int,
    @SerializedName("total_items_count")
    val totalItemsCount: Int,
    @SerializedName("next")
    val nextPage: String?,
    @SerializedName("previous")
    val previousPage: String?,
    @SerializedName("data")
    val data: List<T>?)