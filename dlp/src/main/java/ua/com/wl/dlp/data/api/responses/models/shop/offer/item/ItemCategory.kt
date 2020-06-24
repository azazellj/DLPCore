package ua.com.wl.dlp.data.api.responses.models.shop.offer.item

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class ItemCategory(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("timezone")
    val timezone: String)