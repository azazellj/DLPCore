package ua.com.wl.dlp.core.modules

import org.koin.dsl.module
import ua.com.wl.dlp.domain.interactors.AuthInteractor
import ua.com.wl.dlp.domain.interactors.impl.AuthInteractorImpl

/**
 * @author Denis Makovskyi
 */

val interactorsModule = module {
    single<AuthInteractor> {
        AuthInteractorImpl(
            authPreferences = get(),
            api = get(),
            errorsMapper = get())
    }
}