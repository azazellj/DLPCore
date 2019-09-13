package ua.com.wl.dlp.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import ua.com.wl.dlp.data.db.OrdersDatabase
import ua.com.wl.dlp.data.db.datasources.OrdersDataSource
import ua.com.wl.dlp.data.db.datasources.impl.OrdersDataSourceImpl

/**
 * @author Denis Makovskyi
 */

val databaseModule = module {
    single {
        OrdersDatabase(androidContext())
    }
    single<OrdersDataSource> {
        val db = get<OrdersDatabase>()
        OrdersDataSourceImpl(db.getShopsDao(), db.getOffersDao())
    }
}