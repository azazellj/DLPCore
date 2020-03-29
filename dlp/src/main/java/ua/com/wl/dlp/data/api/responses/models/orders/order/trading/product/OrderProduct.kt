package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderProduct(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("photo")
    val photo: String?,

    @SerializedName("category")
    val category: ProductCategory)