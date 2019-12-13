package ua.com.wl.dlp.data.api.responses.models.consumer.history.endpoints

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderTransactionDetails(@SerializedName("order_number") val number: String)