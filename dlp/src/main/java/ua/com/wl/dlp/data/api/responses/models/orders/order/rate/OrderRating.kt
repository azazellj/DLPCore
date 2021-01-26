package ua.com.wl.dlp.data.api.responses.models.orders.order.rate

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class OrderRating(
    @Json(name = "value")
    val value: Int,

    @Json(name = "comment")
    val comment: String,

    @Json(name = "is_viewed")
    val isViewed: Boolean
) : Parcelable