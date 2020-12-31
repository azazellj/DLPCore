package ua.com.wl.dlp.data.api.models.consumer.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.AccountingType
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.TransactionType

@JsonClass(generateAdapter = true)
data class ProfileOperation(
    @Json(name = "accounting_type")
    val accountingType: AccountingType,

    @Json(name = "operation_type")
    val transactionType: TransactionType?,

    @Json(name = "amount")
    val amount: Int,

    @Json(name = "initial_value")
    val initialValue: Long,

    @Json(name = "resulting_value")
    val resultingValue: Long,

    @Json(name = "initial_value_money")
    val initialMoneyAmount: String,

    @Json(name = "resulting_value_money")
    val resultingMoneyAmount: String,

    @Json(name = "comment")
    val comment: String?,

    @Json(name = "created_at")
    val createdAt: String
)