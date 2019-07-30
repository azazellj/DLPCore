package ua.com.wl.dlp.data.events

/**
 * @author Denis Makovskyi
 */

data class BalanceEvent(val balance: Long, val isSavedInPreferences: Boolean = true)