package ua.com.wl.dlp.data.api.responses.models.consumer.history.notifications

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class NotificationData(
    @SerializedName("show_push_type")
    val type: PushType?,

    @SerializedName("title_loc_key")
    val titleLocKey: String?,

    @SerializedName("title_loc_args")
    val titleLocArgs: String?,

    @SerializedName("body_loc_key")
    val bodyLocKey: String?,

    @SerializedName("body_loc_args")
    val bodyLocArgs: String?,

    @SerializedName("badge")
    val badge: String,

    @SerializedName("sound")
    val sound: String?,

    @SerializedName("click_action")
    val clickAction: String,

    @SerializedName("shop_id")
    val shopId: Int?,

    @SerializedName("offer_id")
    val offerId: Int?,

    @SerializedName("rate_id")
    val orderId: Int?,

    @SerializedName("coupon_id")
    val couponId: Int?,

    @SerializedName("news_item_id")
    val articleId: Int?,

    @SerializedName("promotion_id")
    val promotionId: Int?,

    @SerializedName("pre_order_id")
    val preOrderId: Int?,

    @SerializedName("shop")
    val relatedShop: String?

    /*@SerializedName("shops")
    val relatedShops: List<String>?*/)