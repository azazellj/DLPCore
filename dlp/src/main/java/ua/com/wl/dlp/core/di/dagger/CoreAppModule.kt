package ua.com.wl.dlp.core.di.dagger

import android.app.Application
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
open class CoreAppModule {

    @Inject
    lateinit var application: Application

    @Provides
    @Singleton
    open fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    open fun provideMoshiConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi).asLenient()
    }
}