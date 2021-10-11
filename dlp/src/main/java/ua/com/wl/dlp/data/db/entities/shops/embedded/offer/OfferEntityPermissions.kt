package ua.com.wl.dlp.data.db.entities.shops.embedded.offer

import androidx.room.ColumnInfo

data class OfferEntityPermissions(
    @ColumnInfo(name = "pre_order")
    val isAvailableForPreOrder: Boolean? = null,
    @ColumnInfo(name = "delivery")
    val isAvailableForDelivery: Boolean? = null
)