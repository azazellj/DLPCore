package ua.com.wl.dlp.data.api.responses.models.consumer.history.endpoints

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class NewsItemTransactionDetails constructor(
    @SerializedName("id") 
    val id: Int,
    
    @SerializedName("name")
    val name: String)