package ua.com.wl.dlp.data.events

data class BalanceInvalidationEvent(val balance: Long, val isSavedInPreferences: Boolean = true)