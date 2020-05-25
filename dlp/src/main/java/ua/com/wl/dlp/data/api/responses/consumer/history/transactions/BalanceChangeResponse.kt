package ua.com.wl.dlp.data.api.responses.consumer.history.transactions

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.BalanceChange

/**
 * @author Denis Makovskyi
 */

data class BalanceChangeResponse(
    @SerializedName("balance_change")
    val change: BalanceChange?)