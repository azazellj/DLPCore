package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types

import com.squareup.moshi.Json

enum class OperationType {
    @Json(name = "OFFER_VIEW")
    OFFER_VIEW,

    @Json(name = "NEWS_ITEM_VIEW")
    ARTICLE_VIEW
}