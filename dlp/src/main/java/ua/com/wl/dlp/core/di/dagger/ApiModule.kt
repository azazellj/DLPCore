package ua.com.wl.dlp.core.di.dagger

import java.util.concurrent.TimeUnit

import javax.inject.Named
import javax.inject.Singleton

import android.content.Context
import android.content.pm.PackageManager

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson

import dagger.Module
import dagger.Provides

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import ua.com.wl.dlp.core.DLPCore
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.core.network.AuthInterceptor
import ua.com.wl.dlp.core.network.SessionAuthenticator
import ua.com.wl.dlp.data.api.*
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.prefereces.CorePreferences

/**
 * @author Denis Makovskyi
 */

@Module(includes = [AppModule::class, PreferencesModule::class])
class ApiModule {

    // - Host url and app id from manifest meta data
    @Provides
    @Named(Constants.DI_NAMED_URL)
    fun provideUrl(context: Context): String {
        val key = when(DLPCore.environment) {
            DLPCore.Environment.DEVELOPING -> Constants.META_STAGING_URL
            DLPCore.Environment.PRODUCTION -> Constants.META_PRODUCTION_URL
        }
        return context
            .packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            .metaData?.getString(key) ?: throw IllegalStateException("Server url was not found")
    }

    @Provides
    @Named(Constants.DI_NAMED_APP_IDS)
    fun provideAppIds(context: Context): List<String> {
        val key = when(DLPCore.environment) {
            DLPCore.Environment.DEVELOPING -> Constants.META_STAGING_APP_IDS
            DLPCore.Environment.PRODUCTION -> Constants.META_PRODUCTION_APP_IDS
        }
        val metaValue = context
            .packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            .metaData.get(key)
        return when(metaValue) {
            is Int -> context.resources.getStringArray(metaValue).toList()
            is String -> listOf(metaValue)
            else -> throw IllegalStateException("Application id was not found")
        }
    }

    // - Interceptors
    @Provides
    @Singleton
    fun provideAuthInterceptor(
        @Named(Constants.DI_NAMED_APP_IDS) appIds: List<String>,
        corePreferences: CorePreferences
    ): AuthInterceptor {
        return AuthInterceptor(appIds, corePreferences)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (DLPCore.debuggable) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    // - OKHTTP client for session retrofit
    @Provides
    @Named(Constants.DI_NAMED_SESSION_OKHTTP)
    fun provideSessionClient(
        context: Context,
        authInterceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .readTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .apply {
                if (DLPCore.debuggable) {
                    addInterceptor(interceptor = ChuckerInterceptor(context))
                }
            }
            .build()
    }

    // - Session retrofit - for anonymous api calls
    @Provides
    @Named(Constants.DI_NAMED_SESSION_RETROFIT)
    fun provideSessionRetrofit(
        @Named(Constants.DI_NAMED_URL) url: String,
        gson: Gson,
        @Named(Constants.DI_NAMED_SESSION_OKHTTP) client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    // - Authenticator
    @Provides
    fun provideSessionAuthenticator(
        @Named(Constants.DI_NAMED_SESSION_RETROFIT) retrofit: Retrofit,
        authInterceptor: AuthInterceptor,
        corePreferences: CorePreferences
    ): SessionAuthenticator {
        return SessionAuthenticator(retrofit, authInterceptor, corePreferences)
    }

    // - OKHTTP client for api retrofit
    @Provides
    @Named(Constants.DI_NAMED_API_OKHTTP)
    fun provideApiClient(
        context: Context,
        authInterceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        sessionAuthenticator: SessionAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .readTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .apply {
                if (DLPCore.debuggable) {
                    addInterceptor(interceptor = ChuckerInterceptor(context))
                }
            }
            .authenticator(sessionAuthenticator)
            .build()
    }

    // - Api retrofit - for regular apis
    @Provides
    @Named(Constants.DI_NAMED_API_RETROFIT)
    @Singleton
    fun provideApiRetrofit(
        @Named(Constants.DI_NAMED_URL) url: String,
        gson: Gson,
        @Named(Constants.DI_NAMED_API_OKHTTP) client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    // - Apis implementations
    @Provides
    fun provideErrorsMapper(gson: Gson): ErrorsMapper {
        return ErrorsMapper(gson)
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
    fun provideShopsApiV1(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): ShopApiV1 {
        return retrofit.create(ShopApiV1::class.java)
    }

    @Provides
    fun provideShopsApiV2(@Named(Constants.DI_NAMED_API_RETROFIT) retrofit: Retrofit): ShopApiV2 {
        return retrofit.create(ShopApiV2::class.java)
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