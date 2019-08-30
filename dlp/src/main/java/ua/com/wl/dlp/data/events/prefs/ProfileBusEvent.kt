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
        QR_CODE,
        INVITE_CODE,
        REFERRAL_CODE
    }

    sealed class FieldValue {

        data class LongValue(val value: Long?) : FieldValue()

        data class StringValue(val value: String?) : FieldValue()

        data class CityObjectValue(val value: City?) : FieldValue()

        data class GenderObjectValue(val value: Gender?) : FieldValue()
    }

    data class Change(val saved: Boolean, val field: Field, val value: FieldValue)
}