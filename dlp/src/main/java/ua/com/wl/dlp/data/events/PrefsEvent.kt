package ua.com.wl.dlp.data.events

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class PrefsEvent(
    val saved: Boolean,
    val value: Any?,
    val storage: Storage,
    val destination: Destination,
    val changedField: ChangedField) : BusEvent() {

    enum class Storage {
        CONSUMER
    }

    enum class Destination {
        PROFILE
    }

    enum class ChangedField {
        NAME,
        CITY,
        PHONE,
        EMAIL,
        BALANCE,
        QR_CODE,
        INVITE_CODE,
        REFERRAL_CODE
    }
}