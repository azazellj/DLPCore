package ua.com.wl.dlp.data.api.responses.consumer.history

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.types.AccountingType
import ua.com.wl.dlp.data.api.responses.models.consumer.history.TransactionDetails

/**
 * @author Denis Makovskyi
 */

data class TransactionResponse(
    @SerializedName("accounting_type") val accountingType: AccountingType,
    @SerializedName("initial_value") val initialValue: Int,
    @SerializedName("resulting_value") val resultingValue: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("detail") val details: TransactionDetails)