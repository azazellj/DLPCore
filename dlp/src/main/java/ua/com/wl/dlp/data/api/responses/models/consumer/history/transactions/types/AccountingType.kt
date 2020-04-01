package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class AccountingType(val value: String) {
    @SerializedName("INCOME")
    INCOME("INCOME"),
    @SerializedName("EXPENSE")
    EXPENSE("EXPENSE")
}