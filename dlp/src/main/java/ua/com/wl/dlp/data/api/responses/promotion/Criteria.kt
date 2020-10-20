package ua.com.wl.dlp.data.api.responses.promotion

import com.google.gson.annotations.SerializedName
import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange

data class Criteria(
    @SerializedName("date_range")
    val dateRange: NoveltyDatesRange?,

    @SerializedName("receipt_total_price")
    var receiptTotalPrice: String = ""

)