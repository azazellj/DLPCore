package ua.com.wl.dlp.data.api.responses.consumer.history.transactions

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.TransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.AccountingType

/**
 * @author Denis Makovskyi
 */

data class TransactionResponse(
    @SerializedName("accounting_type")
    val accountingType: AccountingType,

    @SerializedName("initial_value")
    val initialValue: Long,

    @SerializedName("resulting_value")
    val resultingValue: Long,

    @SerializedName("initial_value_money")
    val initialMoneyAmount: String,

    @SerializedName("resulting_value_money")
    val resultingMoneyAmount: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("detail")
    val details: TransactionDetails
)