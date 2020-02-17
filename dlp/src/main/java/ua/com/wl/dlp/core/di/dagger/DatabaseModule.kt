package ua.com.wl.dlp.core.di.dagger

import javax.inject.Singleton

import android.content.Context

import dagger.Module
import dagger.Provides

import ua.com.wl.dlp.data.db.ShopsDatabase
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.data.db.datasources.impl.ShopsDataSourceImpl

/**
 * @author Denis Makovskyi
 */

@Module(includes = [AppModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideShopsDatabase(context: Context): ShopsDatabase {
        return ShopsDatabase(context)
    }

    @Provides
    @Singleton
    fun provideShopsDataSource(database: ShopsDatabase): ShopsDataSource {
        return ShopsDataSourceImpl(
            database.getShopsDao(),
            database.getOffersDao(),
            database.getShopsOffersDao())
    }
}