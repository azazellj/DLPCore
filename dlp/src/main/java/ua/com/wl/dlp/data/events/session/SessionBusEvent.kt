package ua.com.wl.dlp.data.events.session

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class SessionBusEvent(val fallbackType: FallbackType) : BusEvent() {

    enum class FallbackType {
        SIGNED_OUT,
        TOKEN_EXPIRED
    }
}