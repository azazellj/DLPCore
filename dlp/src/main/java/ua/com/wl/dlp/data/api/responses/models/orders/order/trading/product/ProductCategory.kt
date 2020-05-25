package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import com.google.gson.annotations.SerializedName

data class ProductCategory(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String)