package ua.com.wl.dlp.core.di

import java.util.concurrent.TimeUnit

import android.content.pm.PackageManager

import com.chuckerteam.chucker.api.ChuckerInterceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.dsl.module
import org.koin.core.qualifier.named
import org.koin.android.ext.koin.androidContext

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import ua.com.wl.dlp.core.network.AuthInterceptor
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.core.DLPCore
import ua.com.wl.dlp.core.network.SessionAuthenticator
import ua.com.wl.dlp.data.api.*
import ua.com.wl.dlp.data.api.errors.ErrorsMapper

/**
 * @author Denis Makovskyi
 */

val apiModule = module {
    // - Host url and app id from manifest meta data
    factory(qualifier = named(Constants.KOIN_NAMED_URL)) {
        val key = when(DLPCore.environment) {
            DLPCore.Environment.DEVELOPING -> Constants.META_STAGING_URL
            DLPCore.Environment.PRODUCTION -> Constants.META_PRODUCTION_URL
        }
        androidContext()
            .packageManager.getApplicationInfo(androidContext().packageName, PackageManager.GET_META_DATA)
            .metaData?.get(key)?.toString() ?: throw IllegalStateException("Server url was not found")
    }
    factory(qualifier = named(Constants.KOIN_NAMED_APP_ID)) {
        val key = when(DLPCore.environment) {
            DLPCore.Environment.DEVELOPING -> Constants.META_STAGING_APP_ID
            DLPCore.Environment.PRODUCTION -> Constants.META_PRODUCTION_APP_ID
        }
        androidContext()
            .packageManager.getApplicationInfo(androidContext().packageName, PackageManager.GET_META_DATA)
            .metaData?.get(key)?.toString() ?: throw IllegalStateException("Application id was not found")
    }
    // - Interceptors
    factory {
        AuthInterceptor(
            appId = get(qualifier = named(Constants.KOIN_NAMED_APP_ID)),
            corePreferences = get())
    }
    factory {
        HttpLoggingInterceptor().apply {
            level = if(DLPCore.debuggable) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    // - OKHTTP client for session retrofit
    factory(qualifier = named(Constants.KOIN_NAMED_SESSION_OKHTTP)) {
        OkHttpClient.Builder()
            .connectTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .readTimeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(interceptor = get<AuthInterceptor>())
            .addInterceptor(interceptor = get<HttpLoggingInterceptor>())
            .apply {
                if (DLPCore.debuggable) {
                    addInterceptor(interceptor = ChuckerInterceptor(androidContext()))
                }
            }
            .build()
    }
    // - Session retrofit - for anonymous api calls
    factory(qualifier = named(Constants.KOIN_NAMED_SESSION_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(get<String>(qualifier = named(Constants.KOIN_NAMED_URL)))
            .client(get<OkHttpClient>(qualifier = named(Constants.KOIN_NAMED_SESSION_OKHTTP)))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    // - Authenticator
    factory {
        SessionAuthenticator(
            retrofit = get(qualifier = named(Constants.KOIN_NAMED_SESSION_RETROFIT)),
            appId = get(qualifier = named(Constants.KOIN_NAMED_APP_ID)),
            corePreferences = get())
    }
    // - OKHTTP client for api retrofit
    factory(qualifier = named(Constants.KOIN_NAMED_API_OKHTTP)) {
        OkHttpClient.Builder()
            .connectTimeout(TimeUnit.SECONDS.toMillis(30), TimeUnit.SECONDS)
            .readTimeout(TimeUnit.SECONDS.toMillis(30), TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(interceptor = get<AuthInterceptor>())
            .addInterceptor(interceptor = get<HttpLoggingInterceptor>())
            .authenticator(authenticator = get<SessionAuthenticator>())
            .apply {
                if (DLPCore.debuggable) {
                    addInterceptor(interceptor = ChuckerInterceptor(androidContext()))
                }
            }
            .build()
    }
    // - Api retrofit - for regular apis
    single(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(get<String>(qualifier = named(Constants.KOIN_NAMED_URL)))
            .client(get<OkHttpClient>(qualifier = named(Constants.KOIN_NAMED_API_OKHTTP)))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    // - Apis implementations
    factory {
        ErrorsMapper(gson = get())
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(AuthApiV1::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(AuthApiV2::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(ConsumerApiV1::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(ConsumerApiV2::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(NewsApiV1::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(ShopApiV1::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(ShopApiV2::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(OffersApiV1::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(OrdersApiV1::class.java)
    }
    factory {
        get<Retrofit>(qualifier = named(Constants.KOIN_NAMED_API_RETROFIT))
            .create(OrdersApiV2::class.java)
    }
}