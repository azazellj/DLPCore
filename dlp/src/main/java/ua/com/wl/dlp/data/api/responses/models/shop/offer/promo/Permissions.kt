package ua.com.wl.dlp.data.api.responses.models.shop.offer.promo

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Permissions(
    @Json(name = "pre_order")
    val isAvailableForPreOrder: Boolean? = null,
    @Json(name = "delivery")
    val isAvailableForDelivery: Boolean? = null,
) : Parcelable