package ua.com.wl.dlp.core.modules

import org.koin.dsl.module

import ua.com.wl.dlp.data.prefereces.CorePreferences

/**
 * @author Denis Makovskyi
 */

val preferencesModule = module {
    single {
        CorePreferences(context = get())
    }
}