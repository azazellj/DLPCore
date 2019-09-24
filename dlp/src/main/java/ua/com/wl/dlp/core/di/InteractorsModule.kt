package ua.com.wl.dlp.core.di

import org.koin.dsl.module

import ua.com.wl.dlp.domain.interactors.*
import ua.com.wl.dlp.domain.interactors.impl.*

/**
 * @author Denis Makovskyi
 */

val interactorsModule = module {
    single<AuthInteractor> {
        AuthInteractorImpl(
            errorsMapper = get(),
            apiV1 = get(),
            apiV2 = get(),
            corePreferences = get(),
            consumerPreferences = get())
    }
    single<OffersInteractor> {
        OffersInteractorImpl(
            errorsMapper = get(),
            api = get())
    }
    single<ConsumerInteractor> {
        ConsumerInteractorImpl(
            errorsMapper = get(),
            app = get(),
            apiV1 = get(),
            apiV2 = get(),
            offersInteractor = get(),
            consumerPreferences = get())
    }
    single<NewsFeedInteractor> {
        NewsFeedInteractorImpl(
            errorsMapper = get(),
            apiV1 = get())
    }
    single<ShopInteractor> {
        ShopInteractorImpl(
            errorsMapper = get(),
            apiV1 = get(),
            apiV2 = get(),
            shopsDataSource = get(),
            offersInteractor = get())
    }
}