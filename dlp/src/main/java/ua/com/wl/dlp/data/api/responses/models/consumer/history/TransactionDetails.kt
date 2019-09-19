package ua.com.wl.dlp.data.api.responses.models.consumer.history

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.consumer.history.endpoints.NewsItemTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.endpoints.OfferTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.endpoints.OrderTransactionDetails
import ua.com.wl.dlp.data.api.responses.models.consumer.history.types.TransactionType

/**
 * @author Denis Makovskyi
 */

data class TransactionDetails(
    @SerializedName("operation_type") 
    val transactionType: TransactionType,
    
    @SerializedName("amount") 
    val amount: Int,
    
    @SerializedName("comment") 
    val comment: String?,
    
    @SerializedName("offer") 
    val offerDetails: OfferTransactionDetails?,
    
    @SerializedName("order") 
    val orderDetails: OrderTransactionDetails?,
    
    @SerializedName("news_item") 
    val newsItemDetails: NewsItemTransactionDetails?)