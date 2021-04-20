package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.ArticleTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.OfferTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.OrderTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.ShopTransactionsDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.TransactionType

@JsonClass(generateAdapter = true)
data class TransactionDetails(
    @Json(name = "operation_type")
    val transactionType: TransactionType?,

    @Json(name = "amount")
    val amount: Int,

    @Json(name = "amount_money")
    val money: Float,

    @Json(name = "comment")
    val comment: String?,

    @Json(name = "shop")
    val shopDetails: ShopTransactionsDetails? = null,

    @Json(name = "offer")
    val offerDetails: OfferTransactionDetails? = null,

    @Json(name = "order")
    val orderDetails: OrderTransactionDetails? = null,

    @Json(name = "news_item")
    val articleDetails: ArticleTransactionDetails? = null
)