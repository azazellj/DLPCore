package ua.com.wl.dlp.data.events.prefs

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

open class PrefsEvent(val saved: Boolean): BusEvent()