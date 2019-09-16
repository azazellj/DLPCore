package ua.com.wl.dlp.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import ua.com.wl.dlp.data.db.ShopsDatabase
import ua.com.wl.dlp.data.db.datasources.ShopsDataSource
import ua.com.wl.dlp.data.db.datasources.impl.ShopsDataSourceImpl

/**
 * @author Denis Makovskyi
 */

val databaseModule = module {
    single {
        ShopsDatabase(androidContext())
    }
    single<ShopsDataSource> {
        val db = get<ShopsDatabase>()
        ShopsDataSourceImpl(db.getShopsDao(), db.getOffersDao())
    }
}