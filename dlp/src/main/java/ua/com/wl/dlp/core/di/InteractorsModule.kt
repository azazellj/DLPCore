package ua.com.wl.dlp.core.di

import org.koin.dsl.module

import ua.com.wl.dlp.domain.interactors.AuthInteractor
import ua.com.wl.dlp.domain.interactors.ConsumerInteractor
import ua.com.wl.dlp.domain.interactors.NewsFeedInteractor
import ua.com.wl.dlp.domain.interactors.ShopInteractor
import ua.com.wl.dlp.domain.interactors.impl.AuthInteractorImpl
import ua.com.wl.dlp.domain.interactors.impl.ConsumerInteractorImpl
import ua.com.wl.dlp.domain.interactors.impl.NewsFeedInteractorImpl
import ua.com.wl.dlp.domain.interactors.impl.ShopInteractorImpl

/**
 * @author Denis Makovskyi
 */

val interactorsModule = module {
    single<AuthInteractor> {
        AuthInteractorImpl(
            api = get(),
            errorsMapper = get(),
            corePreferences = get(),
            consumerPreferences = get())
    }
    single<ConsumerInteractor> {
        ConsumerInteractorImpl(
            app = get(),
            errorsMapper = get(),
            apiV1 = get(),
            apiV3 = get(),
            consumerPreferences = get())
    }
    single<ShopInteractor> {
        ShopInteractorImpl(
            errorsMapper = get(),
            apiV1 = get(),
            shopsDataSource = get())
    }
    single<NewsFeedInteractor> {
        NewsFeedInteractorImpl(
            errorsMapper = get(),
            apiV1 = get())
    }
}