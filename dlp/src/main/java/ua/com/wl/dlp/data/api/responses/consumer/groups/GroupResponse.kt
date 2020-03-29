package ua.com.wl.dlp.data.api.responses.consumer.groups

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.shop.SimpleShop

/**
 * @author Denis Makovskyi
 */

data class GroupResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("cash_back_percentage")
    val cashBackPercentage: Int,

    @SerializedName("shop")
    val orderShop: SimpleShop)