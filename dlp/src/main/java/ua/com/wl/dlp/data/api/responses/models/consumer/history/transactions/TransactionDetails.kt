package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types.TransactionType
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.ShopTransactionsDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.OfferTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.OrderTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.endpoints.ArticleTransactionDetails

/**
 * @author Denis Makovskyi
 */

data class TransactionDetails(
    @SerializedName("operation_type") 
    val transactionType: TransactionType?,
    
    @SerializedName("amount") 
    val amount: Int,

    @SerializedName("amount_money")
    val money: Float,
    
    @SerializedName("comment") 
    val comment: String?,

    @SerializedName("shop")
    val shopDetails: ShopTransactionsDetails?,

    @SerializedName("offer") 
    val offerDetails: OfferTransactionDetails?,
    
    @SerializedName("order") 
    val orderDetails: OrderTransactionDetails?,
    
    @SerializedName("news_item") 
    val articleDetails: ArticleTransactionDetails?)