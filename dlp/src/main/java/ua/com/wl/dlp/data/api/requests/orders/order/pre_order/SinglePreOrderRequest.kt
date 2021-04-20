package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.PreOrderItem

@JsonClass(generateAdapter = true)
class SinglePreOrderRequest(
    // MultiPreOrderRequest fields

    @Json(name = "shop")
    val shopId: Int,

    @Json(name = "readiness_date")
    val readinessDate: String,

    @Json(name = "readiness_time")
    val readinessTime: String,

    @Json(name = "pay_with_bonuses")
    val useBonuses: Boolean?,

    @Json(name = "count_of_bonuses")
    val bonusesCount: Long?,

    @Json(name = "comment")
    val comment: String?,

    @Json(name = "receipt")
    val receipt: List<PreOrderItem>,

    // SinglePreOrderRequest fields
    @Json(name = "info")
    val info: BasePreOrderInfo? = null
)