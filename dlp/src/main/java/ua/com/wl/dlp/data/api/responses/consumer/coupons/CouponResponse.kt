package ua.com.wl.dlp.data.api.responses.consumer.coupons


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.com.wl.dlp.data.api.models.shop.SimpleShop
import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.consumer.coupons.CouponStatus
import ua.com.wl.dlp.data.api.responses.models.consumer.coupons.CouponType
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse

@JsonClass(generateAdapter = true)
data class CouponResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "type")
    val type: CouponType,

    @Json(name = "status")
    val status: CouponStatus,

    @Json(name = "name")
    val name: String,

    @Json(name = "barcode")
    val barcode: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "image")
    val imageUrl: String?,

    @Json(name = "used")
    val isUsed: Boolean,

    @Json(name = "count_used")
    val usedCount: Int,

    @Json(name = "money_spent")
    val moneySpent: String,

    @Json(name = "count_available")
    val countAvailable: Int?,

    @Json(name = "monetary_available")
    val monetaryAvailable: String?,

    @Json(name = "offer")
    val offer: OfferResponse?,

    @Json(name = "date_range")
    val dateRange: NoveltyDatesRange?,

    @Json(name = "shops")
    val shops: List<SimpleShop>
)