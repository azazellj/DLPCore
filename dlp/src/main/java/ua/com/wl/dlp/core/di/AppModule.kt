package ua.com.wl.dlp.core.di

import com.google.gson.GsonBuilder

import org.koin.dsl.module

/**
 * @author Denis Makovskyi
 */

val appModule = module {
    single {
        GsonBuilder()
            .setLenient()
            .create()
    }
}