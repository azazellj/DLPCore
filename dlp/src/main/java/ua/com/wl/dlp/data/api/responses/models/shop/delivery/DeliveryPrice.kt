package ua.com.wl.dlp.data.api.responses.models.shop.delivery

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */
data class DeliveryPrice(
    @SerializedName("id")
    val id: Int,

    @SerializedName("price")
    val price: Long,

    @SerializedName("total_price_from")
    val totalPriceFrom: String)