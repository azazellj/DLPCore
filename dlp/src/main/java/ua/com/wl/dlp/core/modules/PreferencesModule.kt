package ua.com.wl.dlp.core.modules

import org.koin.dsl.module
import ua.com.wl.dlp.data.prefereces.AuthPreferences

/**
 * @author Denis Makovskyi
 */

val preferencesModule = module {
    single {
        AuthPreferences(context = get())
    }
}