package ua.com.wl.dlp.data.api.requests.orders.order.pre_order

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.models.orders.order.pre_order.BasePreOrderInfo
import ua.com.wl.dlp.data.api.requests.models.orders.order.pre_order.PreOrderItem

/**
 * @author Denis Makovskyi
 */

class SinglePreOrderRequest(
    @SerializedName("info")
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
) {

    class Builder : MultiPreOrderRequest.Builder() {

        private var info: BasePreOrderInfo? = null

        fun info(init: () -> BasePreOrderInfo) {
            info = init()
        }

        fun build(init: SinglePreOrderRequest.Builder.() -> Unit): SinglePreOrderRequest {
            init()
            return SinglePreOrderRequest(
                info, shopId, readinessDate,
                readinessTime, useBonuses,
                bonusesCount, comment, receipt)
        }
    }
}

fun preOrderRequest(init: SinglePreOrderRequest.Builder.() -> Unit): SinglePreOrderRequest {
    return SinglePreOrderRequest.Builder().build(init)
}