package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.shop.SimpleShop
import ua.com.wl.dlp.data.api.models.orders.order.pre_order.PreOrderInfo

/**
 * @author Denis Makovskyi
 */

data class GeneralPreOrderItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("status")
    val status: PreOrderStatus?,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("changed_at")
    val changedAt: String,

    @SerializedName("readiness_date")
    val readinessDate: String,

    @SerializedName("readiness_time")
    val readinessTime: String,

    @SerializedName("total_price")
    val totalPrice: String,

    @SerializedName("pay_with_bonuses")
    val usedBonuses: Boolean,

    @SerializedName("count_of_bonuses")
    val bonusesCount: Long,

    @SerializedName("count_of_bonuses_money")
    val currencyEquivalent: String,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("shop")
    val shop: SimpleShop,

    @SerializedName("info")
    val info: PreOrderInfo?,

    @SerializedName("receipt")
    val receipt: List<PreOrderReceipt>)