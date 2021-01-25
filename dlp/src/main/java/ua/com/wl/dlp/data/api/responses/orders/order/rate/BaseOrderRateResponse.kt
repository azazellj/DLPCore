package ua.com.wl.dlp.data.api.responses.orders.order.rate

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ua.com.wl.dlp.data.api.responses.models.orders.order.rate.Order

@JsonClass(generateAdapter = true)
@Parcelize
open class BaseOrderRateResponse(
    @Json(name = "value")
    var value: Int = 0,

    @Json(name = "comment")
    var comment: String? = null,

    @Json(name = "order")
    var order: Order? = null
) : Parcelable