package ua.com.wl.dlp.data.api.responses.models.orders.order.trading

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class OrderOffer(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "thumb_image")
    val thumbImage: String?
) : Parcelable