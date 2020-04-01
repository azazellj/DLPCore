package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class OperationType(val value: String) {
    @SerializedName("OFFER_VIEW")
    OFFER_VIEW("OFFER_VIEW"),
    @SerializedName("NEWS_ITEM_VIEW")
    ARTICLE_VIEW("NEWS_ITEM_VIEW")
}