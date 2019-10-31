package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.AuthPrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class CorePreferences(
    context: Context,
    private val gson: Gson
) : BasePreferences(context, CorePreferences::class.java.simpleName) {

    companion object {

        const val KEY_AUTH_PREFS = "auth_prefs"
    }

    var authPrefs: AuthPrefs = AuthPrefs()
        set(value) {
            field = value
            save(KEY_AUTH_PREFS, gson.toJson(field))
        }
        get() {
            val json = getString(KEY_AUTH_PREFS)
            return if (json.isNonNullOrEmpty()) {
                field = gson.fromJson(json, AuthPrefs::class.java)
                field

            } else {
                field = AuthPrefs()
                field
            }
        }

    fun removeAuthPrefs() = remove(KEY_AUTH_PREFS)
}