package ua.com.wl.dlp.data.api.responses.models.shop.offer.item

import com.squareup.moshi.Json

enum class ItemType {
    @Json(name = "PRODUCT")
    PRODUCT,

    @Json(name = "TRADE_ITEM")
    TRADE_ITEM
}