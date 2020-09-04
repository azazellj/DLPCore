package ua.com.wl.dlp.data.api.responses.models.shop.delivery

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */
data class PaymentOption(
    @SerializedName("card")
    val card: Boolean,

    @SerializedName("cash")
    val cash: Boolean,

    @SerializedName("online")
    val online: Boolean)