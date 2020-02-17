package ua.com.wl.dlp.core.di.koin

import org.koin.dsl.module

import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.CorePreferences

/**
 * @author Denis Makovskyi
 */

val preferencesModule = module {
    single {
        CorePreferences(context = get(), gson = get())
    }
    single {
        ConsumerPreferences(context = get(), gson = get())
    }
}