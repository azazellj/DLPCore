package ua.com.wl.dlp.core.di.dagger

import javax.inject.Singleton

import android.app.Application

import dagger.Module
import dagger.Provides

import ua.com.wl.dlp.data.api.*
import ua.com.wl.dlp.data.api.errors.*
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.data.prefereces.*
import ua.com.wl.dlp.domain.interactors.*
import ua.com.wl.dlp.domain.interactors.impl.*

/**
 * @author Denis Makovskyi
 */

@Module(includes = [
    AppModule::class,
    PreferencesModule::class,
    DatabaseModule::class,
    ApiModule::class]
)
class InteractorsModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        errorsMapper: ErrorsMapper,
        authApiV1: AuthApiV1,
        authApiV2: AuthApiV2,
        corePreferences: CorePreferences,
        consumerPreferences: ConsumerPreferences
    ): AuthInteractor {
        return AuthInteractorImpl(
            errorsMapper, authApiV1, authApiV2, corePreferences, consumerPreferences)
    }

    @Provides
    @Singleton
    fun provideOffersInteractor(
        errorsMapper: ErrorsMapper,
        application: Application,
        offersApiV1: OffersApiV1,
        consumerPreferences: ConsumerPreferences
    ): OffersInteractor {
        return OffersInteractorImpl(
            errorsMapper, application, offersApiV1, consumerPreferences)
    }

    @Provides
    @Singleton
    fun provideConsumerInteractor(
        errorsMapper: ErrorsMapper,
        application: Application,
        consumerApiV1: ConsumerApiV1,
        consumerApiV2: ConsumerApiV2,
        offersInteractor: OffersInteractor,
        consumerPreferences: ConsumerPreferences
    ): ConsumerInteractor {
        return ConsumerInteractorImpl(
            errorsMapper, application, consumerApiV1, consumerApiV2, offersInteractor, consumerPreferences)
    }

    @Provides
    @Singleton
    fun provideNewsFeedInteractor(
        errorsMapper: ErrorsMapper,
        application: Application,
        newsApiV1: NewsApiV1,
        consumerPreferences: ConsumerPreferences
    ): NewsFeedInteractor {
        return NewsFeedInteractorImpl(
            errorsMapper, application, newsApiV1, consumerPreferences)
    }

    @Provides
    @Singleton
    fun provideShopInteractor(
        errorsMapper: ErrorsMapper,
        shopApiV1: ShopApiV1,
        shopApiV2: ShopApiV2,
        shopsDataSource: ShopsDataSource,
        offersInteractor: OffersInteractor
    ): ShopInteractor {
        return ShopInteractorImpl(
            errorsMapper, shopApiV1, shopApiV2, shopsDataSource, offersInteractor)
    }

    @Provides
    @Singleton
    fun provideOrdersInteractor(
        errorsMapper: ErrorsMapper,
        ordersApiV1: OrdersApiV1,
        ordersApiV2: OrdersApiV2
    ): OrdersInteractor {
        return OrdersInteractorImpl(errorsMapper, ordersApiV1, ordersApiV2)
    }
}