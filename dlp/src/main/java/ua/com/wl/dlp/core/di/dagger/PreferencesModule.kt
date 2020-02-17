package ua.com.wl.dlp.core.di.dagger

import javax.inject.Singleton

import android.content.Context

import com.google.gson.Gson

import dagger.Module
import dagger.Provides

import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences

/**
 * @author Denis Makovskyi
 */

@Module(includes = [AppModule::class])
class PreferencesModule {

    @Provides
    @Singleton
    fun provideCorePreferences(context: Context, gson: Gson): CorePreferences {
        return CorePreferences(context, gson)
    }

    @Provides
    @Singleton
    fun provideConsumerPreferences(context: Context, gson: Gson): ConsumerPreferences {
        return ConsumerPreferences(context, gson)
    }
}