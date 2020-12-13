package ua.com.wl.dlp.data.api.responses.promotion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange

@JsonClass(generateAdapter = true)
data class Criteria(
    @Json(name = "date_range")
    val dateRange: NoveltyDatesRange?,

    @Json(name = "receipt_total_price")
    var receiptTotalPrice: String = ""
)