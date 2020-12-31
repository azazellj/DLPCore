package ua.com.wl.dlp.data.prefereces

import android.content.Context
import com.squareup.moshi.Moshi
import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.BusinessPrefs
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.data.prefereces.models.RankPrefs
import ua.com.wl.dlp.utils.fromJson
import ua.com.wl.dlp.utils.isNonNullOrEmpty
import ua.com.wl.dlp.utils.toJson

/**
 * @author Denis Makovskyi
 */

class ConsumerPreferences(
    context: Context,
    private val moshi: Moshi
) : BasePreferences(context, ConsumerPreferences::class.java.simpleName) {

    companion object {

        const val KEY_RANK_PREFS = "rank_prefs"
        const val KEY_PROFILE_PREFS = "profile_prefs"
        const val KEY_BUSINESS_PREFS = "business_prefs"
    }

    var rankPrefs: RankPrefs
        set(value) {
            save(KEY_RANK_PREFS, moshi.toJson(value))
        }
        get() {
            val json = getString(KEY_RANK_PREFS)
            return if (json.isNonNullOrEmpty()) {
                moshi.fromJson<RankPrefs>(json) ?: RankPrefs()
            } else {
                RankPrefs()
            }
        }

    var profilePrefs: ProfilePrefs
        set(value) {
            save(KEY_PROFILE_PREFS, moshi.toJson(value))
        }
        get() {
            val json = getString(KEY_PROFILE_PREFS)
            return if (json.isNonNullOrEmpty()) {
                moshi.fromJson<ProfilePrefs>(json) ?: ProfilePrefs()
            } else {
                ProfilePrefs()
            }
        }

    var businessPrefs: BusinessPrefs
        set(value) {
            save(KEY_BUSINESS_PREFS, moshi.toJson(value))
        }
        get() {
            val json = getString(KEY_BUSINESS_PREFS)
            return if (json.isNonNullOrEmpty()) {
                moshi.fromJson<BusinessPrefs>(json) ?: BusinessPrefs()
            } else {
                BusinessPrefs()
            }
        }

    fun removeRankPrefs() = remove(KEY_RANK_PREFS)

    fun removeProfilePrefs() = remove(KEY_PROFILE_PREFS)

    fun removeBusinessPrefs() = remove(KEY_BUSINESS_PREFS)
}