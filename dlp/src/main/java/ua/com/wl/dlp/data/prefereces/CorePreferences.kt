package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.AuthPrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class CorePreferences constructor(
    context: Context,
    private val gson: Gson
) : BasePreferences(context, CorePreferences::class.java.simpleName) {

    companion object {

        const val KEY_AUTH_PREFS = "auth_prefs"
    }

    var authPrefs: AuthPrefs
        set(value) {
            save(KEY_AUTH_PREFS, gson.toJson(value))
        }
        get() {
            val json = getString(KEY_AUTH_PREFS)
            return if (json.isNonNullOrEmpty()) {
                gson.fromJson(json, AuthPrefs::class.java)
            } else {
                AuthPrefs()
            }
        }

    fun removeAuthPrefs() = remove(KEY_AUTH_PREFS)
}