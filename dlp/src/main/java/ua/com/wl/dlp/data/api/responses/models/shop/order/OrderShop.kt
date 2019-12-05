package ua.com.wl.dlp.data.api.responses.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class OrderShop(
    @SerializedName("name")
    val name: String,

    @SerializedName("city")
    val city: OrderShopCity
): BaseOrderShop()