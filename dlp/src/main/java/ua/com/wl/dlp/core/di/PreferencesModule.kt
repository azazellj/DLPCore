package ua.com.wl.dlp.core.di

import org.koin.dsl.module

import ua.com.wl.dlp.data.prefereces.CorePreferences

/**
 * @author Denis Makovskyi
 */

val preferencesModule = module {
    single {
        CorePreferences(context = get(), gson = get())
    }
}