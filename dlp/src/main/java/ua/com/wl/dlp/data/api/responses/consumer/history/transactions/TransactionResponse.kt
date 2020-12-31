package ua.com.wl.dlp.data.api.responses.consumer.history.transactions

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.TransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.AccountingType

@JsonClass(generateAdapter = true)
data class TransactionResponse(
    @Json(name = "accounting_type")
    val accountingType: AccountingType,

    @Json(name = "initial_value")
    val initialValue: Long,

    @Json(name = "resulting_value")
    val resultingValue: Long,

    @Json(name = "initial_value_money")
    val initialMoneyAmount: String,

    @Json(name = "resulting_value_money")
    val resultingMoneyAmount: String,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "detail")
    val details: TransactionDetails
)