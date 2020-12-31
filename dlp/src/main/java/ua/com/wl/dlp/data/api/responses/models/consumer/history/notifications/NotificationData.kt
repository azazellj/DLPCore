package ua.com.wl.dlp.data.api.responses.models.consumer.history.notifications

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationData(
    @Json(name = "show_push_type")
    val type: PushType?,

    @Json(name = "title_loc_key")
    val titleLocKey: String?,

    /*@Json(name = "title_loc_args")
    val titleLocArgs: List<String>?,*/

    @Json(name = "body_loc_key")
    val bodyLocKey: String?,

    /*@Json(name = "body_loc_args")
    val bodyLocArgs: List<String>?,*/

    @Json(name = "badge")
    val badge: String,

    @Json(name = "sound")
    val sound: String?,

    @Json(name = "click_action")
    val clickAction: String,

    @Json(name = "shop_id")
    val shopId: Int?,

    @Json(name = "offer_id")
    val offerId: Int?,

    @Json(name = "rate_id")
    val orderId: Int?,

    @Json(name = "coupon_id")
    val couponId: Int?,

    @Json(name = "news_item_id")
    val articleId: Int?,

    @Json(name = "promotion_id")
    val promotionId: Int?,

    @Json(name = "pre_order_id")
    val preOrderId: Int?,

    @Json(name = "shop")
    val relatedShop: String?

    /*@Json(name = "shops")
    val relatedShops: List<String>?*/
)