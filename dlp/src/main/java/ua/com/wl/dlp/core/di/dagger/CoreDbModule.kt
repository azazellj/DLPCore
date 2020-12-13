package ua.com.wl.dlp.core.di.dagger

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ua.com.wl.dlp.data.db.ShopsDatabase
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.data.db.datasources.impl.ShopsDataSourceImpl
import javax.inject.Singleton

@Module
open class CoreDbModule {
    @Provides
    @Singleton
    open fun provideDatabase(context: Application): ShopsDatabase {
        return Room.databaseBuilder(context, ShopsDatabase::class.java, ShopsDatabase.NAME)
            // TODO: 12/12/20 disable destructive migration
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    open fun provideShopsDataSource(shopsDatabase: ShopsDatabase): ShopsDataSource {
        return ShopsDataSourceImpl(
            shopsDatabase.getShopsDao(),
            shopsDatabase.getOffersDao(),
            shopsDatabase.getShopsOffersDao()
        )
    }
}