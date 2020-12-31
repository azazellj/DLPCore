package ua.com.wl.dlp.data.api.responses.consumer.history.transactions

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.BalanceChange

@JsonClass(generateAdapter = true)
data class BalanceChangeResponse(
    @Json(name = "balance_change")
    val change: BalanceChange?
)