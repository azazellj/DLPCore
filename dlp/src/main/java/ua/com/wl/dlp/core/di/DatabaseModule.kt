package ua.com.wl.dlp.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import ua.com.wl.dlp.data.db.ShopsDatabase
import ua.com.wl.dlp.data.db.datasources.ShopDataSource
import ua.com.wl.dlp.data.db.datasources.impl.ShopDataSourceImpl

/**
 * @author Denis Makovskyi
 */

val databaseModule = module {
    single {
        ShopsDatabase(androidContext())
    }
    single<ShopDataSource> {
        val db = get<ShopsDatabase>()
        ShopDataSourceImpl(
            db.getShopsDao(),
            db.getOffersDao(),
            db.getShopsOffersDao())
    }
}