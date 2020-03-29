package ua.com.wl.dlp.data.api.models.shop

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SimpleShop(
    @SerializedName("name")
    val name: String,

    @SerializedName("city")
    val city: SimpleShopCity
): BaseSimpleShop()