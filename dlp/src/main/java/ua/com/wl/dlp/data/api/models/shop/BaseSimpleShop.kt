package ua.com.wl.dlp.data.api.models.shop

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BaseSimpleShop(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("thumb_logo")
    val thumbLogo: String? = null)