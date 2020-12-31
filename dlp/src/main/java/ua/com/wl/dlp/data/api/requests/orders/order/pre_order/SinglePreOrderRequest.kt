package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.PreOrderItem

@JsonClass(generateAdapter = true)
class SinglePreOrderRequest(
    @Json(name = "info")
    val info: BasePreOrderInfo?,
    shopId: Int,
    readinessDate: String,
    readinessTime: String,
    useBonuses: Boolean?,
    bonusesCount: Long?,
    comment: String?,
    receipt: List<PreOrderItem>
) : MultiPreOrderRequest(
    shopId,
    readinessDate,
    readinessTime,
    useBonuses,
    bonusesCount,
    comment,
    receipt
)