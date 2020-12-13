package ua.com.wl.dlp.core.di.dagger

import android.app.Application
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.data.prefereces.OrderPreferences
import javax.inject.Singleton

@Module
open class CorePreferencesModule {
    @Provides
    @Singleton
    open fun provideCorePreferences(context: Application, moshi: Moshi): CorePreferences {
        return CorePreferences(context, moshi)
    }

    @Provides
    @Singleton
    open fun provideOrderPreferences(context: Application, moshi: Moshi): OrderPreferences {
        return OrderPreferences(context, moshi)
    }

    @Provides
    @Singleton
    open fun provideConsumerPreferences(context: Application, moshi: Moshi): ConsumerPreferences {
        return ConsumerPreferences(context, moshi)
    }
}