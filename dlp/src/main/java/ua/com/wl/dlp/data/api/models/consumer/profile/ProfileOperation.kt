package ua.com.wl.dlp.data.api.models.consumer.profile

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.AccountingType
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.TransactionType

/**
 * @author Denis Makovskyi
 */

data class ProfileOperation(
    @SerializedName("accounting_type")
    val accountingType: AccountingType,

    @SerializedName("operation_type")
    val transactionType: TransactionType?,

    @SerializedName("amount")
    val amount: Int,

    @SerializedName("initial_value")
    val initialValue: Long,

    @SerializedName("resulting_value")
    val resultingValue: Long,

    @SerializedName("initial_value_money")
    val initialMoneyAmount: String,

    @SerializedName("resulting_value_money")
    val resultingMoneyAmount: String,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("created_at")
    val createdAt: String)