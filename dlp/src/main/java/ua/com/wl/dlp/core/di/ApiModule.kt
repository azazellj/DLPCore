package ua.com.wl.dlp.core.di

import java.util.concurrent.TimeUnit

import android.content.pm.PackageManager

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.dsl.module
import org.koin.core.qualifier.named
import org.koin.android.ext.koin.androidContext

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import ua.com.wl.dlp.BuildConfig
import ua.com.wl.dlp.core.network.AuthInterceptor
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.AuthApi
import ua.com.wl.dlp.data.api.ConsumerApi
import ua.com.wl.dlp.data.api.errors.ErrorsMapper

/**
 * @author Denis Makovskyi
 */

val apiModule = module {
    factory(qualifier = named(Constants.KOIN_NAMED_URL)) {
        val key = if (BuildConfig.DEBUG) Constants.META_STAGING_URL else Constants.META_PRODUCTION_URL
        androidContext()
            .packageManager.getApplicationInfo(androidContext().packageName, PackageManager.GET_META_DATA)
            .metaData?.get(key)?.toString() ?: throw IllegalStateException("Server url was not found")
    }
    factory(qualifier = named(Constants.KOIN_NAMED_APP_ID)) {
        val key = if (BuildConfig.DEBUG) Constants.META_STAGING_APP_ID else Constants.META_PRODUCTION_APP_ID
        androidContext()
            .packageManager.getApplicationInfo(androidContext().packageName, PackageManager.GET_META_DATA)
            .metaData?.get(key)?.toString() ?: throw IllegalStateException("Application id was not found")
    }
    factory {
        AuthInterceptor(
            appId = get(qualifier = named(Constants.KOIN_NAMED_APP_ID)),
            corePreferences = get())
    }
    factory {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(qualifier = named(Constants.KOIN_NAMED_URL)))
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(TimeUnit.SECONDS.toMillis(30), TimeUnit.SECONDS)
                    .readTimeout(TimeUnit.SECONDS.toMillis(30), TimeUnit.SECONDS)
                    .followRedirects(false)
                    .followSslRedirects(false)
                    .addInterceptor(interceptor = get<AuthInterceptor>())
                    .addInterceptor(interceptor = get<HttpLoggingInterceptor>())
                    .build())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single {
        ErrorsMapper(gson = get())
    }
    single {
        get<Retrofit>().create(AuthApi::class.java)
    }
    single {
        get<Retrofit>().create(ConsumerApi::class.java)
    }
}