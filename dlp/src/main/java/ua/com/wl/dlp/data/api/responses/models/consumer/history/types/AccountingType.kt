package ua.com.wl.dlp.data.api.responses.models.consumer.history.types

import com.google.gson.annotations.SerializedName

enum class AccountingType(val value: String) {
    @SerializedName("INCOME")
    INCOME("INCOME"),
    @SerializedName("EXPENSE")
    EXPENSE("EXPENSE")
}