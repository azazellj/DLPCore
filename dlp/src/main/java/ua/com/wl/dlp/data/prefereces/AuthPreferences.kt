package ua.com.wl.dlp.data.prefereces

import android.content.Context
import ua.com.wl.archetype.core.android.preferences.BasePreferences

/**
 * @author Denis Makovskyi
 */

class AuthPreferences(context: Context) : BasePreferences(context, AuthPreferences::class.java.simpleName) {

    companion object {

        const val KEY_AUTH_TOKEN = "auth_token"
    }

    fun authToken(token: String) {
        save(KEY_AUTH_TOKEN, token)
    }

    fun authToken(): String? = getString(KEY_AUTH_TOKEN)

    fun removeAuthToken() = remove(KEY_AUTH_TOKEN)
}