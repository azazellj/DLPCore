package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleTransactionDetails(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
)