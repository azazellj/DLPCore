package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types

import com.squareup.moshi.Json

enum class AccountingType {
    @Json(name = "INCOME")
    INCOME,

    @Json(name = "EXPENSE")
    EXPENSE
}