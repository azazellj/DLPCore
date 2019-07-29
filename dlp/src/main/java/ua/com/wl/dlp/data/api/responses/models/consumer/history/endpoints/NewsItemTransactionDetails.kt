package ua.com.wl.dlp.data.api.responses.models.consumer.history.endpoints

import com.google.gson.annotations.SerializedName

data class NewsItemTransactionDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String)