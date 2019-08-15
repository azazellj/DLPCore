package ua.com.wl.dlp.data.events

import ua.com.wl.archetype.core.android.bus.BusEvent

/**
 * @author Denis Makovskyi
 */

data class BalanceEvent(val balance: Long, val isSavedInPreferences: Boolean = true): BusEvent()