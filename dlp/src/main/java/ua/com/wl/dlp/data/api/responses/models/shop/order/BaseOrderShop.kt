package ua.com.wl.dlp.data.api.responses.models.shop.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BaseOrderShop(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("thumb_logo")
    val thumbLogo: String? = null)