package ua.com.wl.dlp.data.events.prefs

import ua.com.wl.archetype.core.android.bus.BusEvent
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.api.responses.models.consumer.profile.Gender

/**
 * @author Denis Makovskyi
 */

class ProfileBusEvent(val changes: List<Change>) : BusEvent() {

    enum class Field {
        FIRST_NAME,
        PATRONYMIC,
        LAST_NAME,
        CITY,
        PHONE,
        EMAIL,
        GENDER,
        BIRTH_DATE,
        BALANCE,
        MONEY_AMOUNT,
        QR_CODE,
        INVITE_CODE,
        REFERRAL_CODE
    }

    sealed class FieldValue {

        data class LongValue constructor(val value: Long?) : FieldValue()

        data class StringValue constructor(val value: String?) : FieldValue()

        data class CityObjectValue constructor(val value: City?) : FieldValue()

        data class GenderObjectValue constructor(val value: Gender?) : FieldValue()
    }

    data class Change constructor(
        val saved: Boolean,
        val field: Field,
        val value: FieldValue)
}