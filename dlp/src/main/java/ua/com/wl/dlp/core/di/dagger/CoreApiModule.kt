package ua.com.wl.dlp.core.di.dagger

import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.core.network.AuthInterceptor
import ua.com.wl.dlp.core.network.SessionAuthenticator
import ua.com.wl.dlp.data.api.*
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.prefereces.CorePreferences
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class CoreApiModule {

    @Provides
    @Singleton
    open fun provideMetaData(context: Application): Bundle {
        return context.packageManager
            .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            .metaData
            ?: Bundle.EMPTY
    }

    // - Host url and app id from manifest meta data

    @Provides
    @Singleton
    @Named(Constants.DI_NAMED_URL)
    open fun provideBaseUrl(metaData: Bundle): String {
        return metaData.getString(Constants.META_PRODUCTION_URL)
            ?: throw IllegalStateException("Server url was not found")
    }

    @Provides
    @Singleton
    @Named(Constants.DI_NAMED_APP_IDS)
    open fun provideAppIds(context: Application, metaData: Bundle): List<String> {
        return when (val metaValue = metaData.get(Constants.META_PRODUCTION_APP_IDS)) {
            is Int -> context.resources.getStringArray(metaValue).toList()
            is String -> listOf(metaValue)
            else -> throw IllegalStateException("Application id was not found")
        }
    }

    // - Interceptors

    @Provides
    open fun provideAuthInterceptor(
        @Named(Constants.DI_NAMED_APP_IDS) appIds: List<String>,
        corePreferences: CorePreferences,
        @Named(Constants.DI_NAMED_REFRESH_RETROFIT)
        retrofit: Retrofit
    ): AuthInterceptor {
        return AuthInterceptor(
            appIds = appIds,
            corePreferences = corePreferences,
            retrofit = retrofit
        )
    }

    @Provides
    open fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    open fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .readTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)
    }

    @Provides
    @Named(Constants.DI_NAMED_SESSION_OKHTTP)
    open fun provideSessionOkHttp(
        builder: OkHttpClient.Builder,
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return builder.run {
            addInterceptor(authInterceptor)
            addInterceptor(loggingInterceptor)
            build()
        }
    }

    @Provides
    @Named(Constants.DI_NAMED_REFRESH_OKHTTP)
    open fun provideRefreshOkHttp(
        builder: OkHttpClient.Builder,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return builder.run {
            addInterceptor(loggingInterceptor)
            build()
        }
    }

    @Provides
    @Named(Constants.DI_NAMED_SESSION_RETROFIT)
    open fun provideSessionRetrofit(
        @Named(Constants.DI_NAMED_URL)
        baseUrl: String,
        @Named(Constants.DI_NAMED_SESSION_OKHTTP)
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            client(client)
            addConverterFactory(converterFactory)
            build()
        }
    }

    @Provides
    @Named(Constants.DI_NAMED_REFRESH_RETROFIT)
    open fun provideRefreshRetrofit(
        @Named(Constants.DI_NAMED_URL)
        baseUrl: String,
        @Named(Constants.DI_NAMED_REFRESH_OKHTTP)
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            client(client)
            addConverterFactory(converterFactory)
            build()
        }
    }

    @Provides
    open fun provideSessionAuthenticator(
        @Named(Constants.DI_NAMED_SESSION_RETROFIT)
        retrofit: Retrofit,
        authInterceptor: AuthInterceptor,
        corePreferences: CorePreferences
    ): SessionAuthenticator {
        return SessionAuthenticator(
            retrofit = retrofit,
            authInterceptor = authInterceptor,
            corePreferences = corePreferences
        )
    }

    @Provides
    @Named(Constants.DI_NAMED_API_OKHTTP)
    open fun provideAppOkHttp(
        builder: OkHttpClient.Builder,
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        sessionAuthenticator: SessionAuthenticator
    ): OkHttpClient {
        return builder.run {
            addInterceptor(authInterceptor)
            addInterceptor(loggingInterceptor)
            authenticator(sessionAuthenticator)
            build()
        }
    }

    @Provides
    @Named(Constants.DI_NAMED_API_RETROFIT)
    open fun provideAppRetrofit(
        @Named(Constants.DI_NAMED_URL)
        baseUrl: String,
        @Named(Constants.DI_NAMED_API_OKHTTP)
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            client(client)
            addConverterFactory(converterFactory)
            build()
        }
    }

    @Provides
    open fun provideErrorsMapper(moshi: Moshi): ErrorsMapper {
        return ErrorsMapper(moshi)
    }

    @Provides
    fun provideAuthApiV1(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): AuthApiV1 {
        return retrofit.create(AuthApiV1::class.java)
    }

    @Provides
    fun provideAuthApiV2(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): AuthApiV2 {
        return retrofit.create(AuthApiV2::class.java)
    }

    @Provides
    fun provideConsumerApiV1(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): ConsumerApiV1 {
        return retrofit.create(ConsumerApiV1::class.java)
    }

    @Provides
    fun provideConsumerApiV2(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): ConsumerApiV2 {
        return retrofit.create(ConsumerApiV2::class.java)
    }

    @Provides
    fun provideNewsApiV1(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): NewsApiV1 {
        return retrofit.create(NewsApiV1::class.java)
    }

    @Provides
    fun provideShopApiV1(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): ShopApiV1 {
        return retrofit.create(ShopApiV1::class.java)
    }

    @Provides
    fun provideShopApiV2(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): ShopApiV2 {
        return retrofit.create(ShopApiV2::class.java)
    }

    @Provides
    fun providePromotionApiV2(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): PromotionApiV2 {
        return retrofit.create(PromotionApiV2::class.java)
    }

    @Provides
    fun provideOffersApiV1(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): OffersApiV1 {
        return retrofit.create(OffersApiV1::class.java)
    }

    @Provides
    fun provideOrdersApiV1(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): OrdersApiV1 {
        return retrofit.create(OrdersApiV1::class.java)
    }

    @Provides
    fun provideOrdersApiV2(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): OrdersApiV2 {
        return retrofit.create(OrdersApiV2::class.java)
    }
}