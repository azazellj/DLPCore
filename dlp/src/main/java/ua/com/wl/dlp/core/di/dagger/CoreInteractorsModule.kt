package ua.com.wl.dlp.core.di.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import ua.com.wl.dlp.data.api.*
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.domain.interactors.*
import ua.com.wl.dlp.domain.interactors.impl.*
import javax.inject.Singleton

@Module
open class CoreInteractorsModule {

    @Provides
    @Singleton
    open fun provideAuthInteractor(
        errorsMapper: ErrorsMapper,
        apiV1: AuthApiV1,
        apiV2: AuthApiV2,
        corePreferences: CorePreferences,
        consumerPreferences: ConsumerPreferences
    ): AuthInteractor {
        return AuthInteractorImpl(
            errorsMapper = errorsMapper,
            apiV1 = apiV1,
            apiV2 = apiV2,
            corePreferences = corePreferences,
            consumerPreferences = consumerPreferences
        )
    }

    @Provides
    @Singleton
    open fun provideConsumerInteractor(
        errorsMapper: ErrorsMapper,
        application: Application,
        apiV1: ConsumerApiV1,
        apiV2: ConsumerApiV2,
        shopsDataSource: ShopsDataSource,
        offersInteractor: OffersInteractor,
        consumerPreferences: ConsumerPreferences
    ): ConsumerInteractor {
        return ConsumerInteractorImpl(
            errorsMapper = errorsMapper,
            app = application,
            apiV1 = apiV1,
            apiV2 = apiV2,
            shopsDataSource = shopsDataSource,
            offersInteractor = offersInteractor,
            consumerPreferences = consumerPreferences
        )
    }

    @Provides
    @Singleton
    open fun provideNewsFeedInteractor(
        errorsMapper: ErrorsMapper,
        application: Application,
        apiV1: NewsApiV1,
        corePreferences: CorePreferences,
        consumerPreferences: ConsumerPreferences
    ): NewsFeedInteractor {
        return NewsFeedInteractorImpl(
            errorsMapper = errorsMapper,
            app = application,
            apiV1 = apiV1,
            consumerPreferences = consumerPreferences
        )
    }

    @Provides
    @Singleton
    open fun provideShopInteractor(
        errorsMapper: ErrorsMapper,
        apiV1: ShopApiV1,
        apiV2: ShopApiV2,
        shopsDataSource: ShopsDataSource,
        offersInteractor: OffersInteractor
    ): ShopInteractor {
        return ShopInteractorImpl(
            errorsMapper = errorsMapper,
            apiV1 = apiV1,
            apiV2 = apiV2,
            shopsDataSource = shopsDataSource,
            offersInteractor = offersInteractor
        )
    }

    @Provides
    @Singleton
    open fun providePromotionInteractor(
        errorsMapper: ErrorsMapper,
        apiV2: PromotionApiV2
    ): PromotionInteractor {
        return PromotionInteractorImpl(
            errorsMapper = errorsMapper,
            apiV2 = apiV2,
        )
    }

    @Provides
    @Singleton
    open fun provideOffersInteractor(
        errorsMapper: ErrorsMapper,
        application: Application,
        apiV1: OffersApiV1,
        shopsDataSource: ShopsDataSource,
        consumerPreferences: ConsumerPreferences
    ): OffersInteractor {
        return OffersInteractorImpl(
            errorsMapper = errorsMapper,
            app = application,
            apiV1 = apiV1,
            shopsDataSource = shopsDataSource,
            consumerPreferences = consumerPreferences
        )
    }

    @Provides
    @Singleton
    open fun provideOrdersInteractor(
        errorsMapper: ErrorsMapper,
        apiV1: OrdersApiV1,
        apiV2: OrdersApiV2,
    ): OrdersInteractor {
        return OrdersInteractorImpl(
            errorsMapper = errorsMapper,
            apiV1 = apiV1,
            apiV2 = apiV2
        )
    }
}