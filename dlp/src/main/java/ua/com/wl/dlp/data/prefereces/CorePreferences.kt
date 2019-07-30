package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.CorePrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class CorePreferences(
    context: Context,
    private val gson: Gson) : BasePreferences(context, CorePreferences::class.java.simpleName) {

    companion object {

        const val KEY_CORE_PREFS = "core_prefs"
    }

    var corePrefs: CorePrefs = CorePrefs()
        set(value) {
            field = value
            save(KEY_CORE_PREFS, gson.toJson(field))
        }
        get() {
            val raw = getString(KEY_CORE_PREFS)
            return if (raw.isNonNullOrEmpty()) {
                field = gson.fromJson(raw, CorePrefs::class.java)
                field

            } else {
                field = CorePrefs()
                field
            }
        }

    fun removeCorePrefs() = remove(KEY_CORE_PREFS)
}