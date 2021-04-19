package ua.com.wl.dlp.data.api.responses.orders.order

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ua.com.wl.dlp.data.api.models.shop.BaseSimpleShop
import ua.com.wl.dlp.data.api.responses.models.orders.order.ConsumerOrder
import ua.com.wl.dlp.data.api.responses.models.orders.order.rate.OrderRating
import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.OrderReceipt
import ua.com.wl.dlp.data.api.responses.models.orders.order.trading.OrderStaffMember

@JsonClass(generateAdapter = true)
@Parcelize
data class OrderResponse(
    // BaseOrderResponse fields
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "opened_at")
    var openedAt: String = "",

    @Json(name = "closed_at")
    var closedAt: String = "",

    @Json(name = "cash_back_amount_in_bonuses_money")
    var moneyCashBack: String? = null,

    @Json(name = "cash_back_amount_in_bonuses")
    var bonusesCashBack: Long? = null,

    @Json(name = "shop")
    var shop: BaseSimpleShop? = null,

    @Json(name = "rating")
    var rating: OrderRating? = null,

    @Json(name = "rs_order")
    var consumerOrder: ConsumerOrder? = null,

    // OrderResponse fields
    @Json(name = "comment")
    val comment: String? = null,

    @Json(name = "receipt")
    val receipt: OrderReceipt? = null,

    @Json(name = "staff_member")
    val staffMember: OrderStaffMember? = null
) : Parcelable