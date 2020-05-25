package ua.com.wl.dlp.data.api.responses.models.orders.order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class ConsumerOrder(
    @SerializedName("rs_order_id")
    val id: String,

    @SerializedName("rs_price_in_money")
    val amount: String?,

    @SerializedName("rs_price_in_bonuses")
    val bonuses: Long?)