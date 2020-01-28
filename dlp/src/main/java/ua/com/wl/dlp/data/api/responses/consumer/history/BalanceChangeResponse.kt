package ua.com.wl.dlp.data.api.responses.consumer.history

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.BalanceChange

/**
 * @author Denis Makovskyi
 */

data class BalanceChangeResponse constructor(
    @SerializedName("balance_change")
    val change: BalanceChange)