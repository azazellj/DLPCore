package ua.com.wl.dlp.data.api.responses.models.shop.delivery

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */
class PaymentOptions(
    @SerializedName("DELIVERY")
    val delivery: PaymentOption?,

    @SerializedName("TAKEAWAY")
    val takeaway: PaymentOption?)