package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.ArticleTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.OfferTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.OperationType

@JsonClass(generateAdapter = true)
data class BalanceChange(
    @Json(name = "operation_type")
    val type: OperationType,

    @Json(name = "id")
    val id: Int,

    @Json(name = "amount")
    val amount: Int,

    @Json(name = "account")
    val account: Int,

    @Json(name = "transfer")
    val transfer: Int,

    @Json(name = "initial_value")
    val initialBalance: Long,

    @Json(name = "resulting_value")
    val resultingBalance: Long,

    @Json(name = "initial_value_money")
    val initialMoneyAmount: String,

    @Json(name = "resulting_value_money")
    val resultingMoneyAmount: String,

    @Json(name = "comment")
    val comment: String?,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "offer")
    val offerDetails: OfferTransactionDetails?,

    @Json(name = "news_item")
    val articleDetails: ArticleTransactionDetails?
)