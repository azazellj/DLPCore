package ua.com.wl.dlp.data.events.session

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class SessionEvent(val isValid: Boolean, val httpCode: Int): BusEvent()