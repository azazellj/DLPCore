package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class ShopTransactionsDetails(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String)