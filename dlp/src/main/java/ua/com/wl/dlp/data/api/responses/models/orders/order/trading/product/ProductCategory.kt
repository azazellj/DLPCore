package ua.com.wl.dlp.data.api.responses.models.orders.order.trading.product

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ProductCategory(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
) : Parcelable