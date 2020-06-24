package ua.com.wl.dlp.data.api.responses.models.shop.offer.item

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class ItemType(val type: String) {
    @SerializedName("PRODUCT")
    PRODUCT("PRODUCT"),
    @SerializedName("TRADE_ITEM")
    TRADE_ITEM("TRADE_ITEM")
}