package ua.com.wl.dlp.data.events.shop.offer

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class OfferBusEvent constructor(
    val offerId: Int,
    val tradeId: Int?,
    val field: Field,
    val value: FieldValue
) : BusEvent() {

    enum class Field {
        IS_FAVOURITE
    }

    sealed class FieldValue {

        data class BooleanValue(val value: Boolean?) : FieldValue()
    }
}