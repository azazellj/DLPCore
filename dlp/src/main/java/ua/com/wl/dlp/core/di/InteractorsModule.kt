package ua.com.wl.dlp.core.di

import org.koin.dsl.module

import ua.com.wl.dlp.domain.interactors.AuthInteractor
import ua.com.wl.dlp.domain.interactors.impl.AuthInteractorImpl

/**
 * @author Denis Makovskyi
 */

val interactorsModule = module {
    single<AuthInteractor> {
        AuthInteractorImpl(
            corePreferences = get(),
            api = get(),
            errorsMapper = get())
    }
}