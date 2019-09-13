package ua.com.wl.dlp.core

import android.app.Application

import org.koin.dsl.koinApplication
import org.koin.core.KoinApplication
import org.koin.android.ext.koin.androidContext

import ua.com.wl.dlp.core.di.*

/**
 * @author Denis Makovskyi
 */

object DLPCore {

    enum class Mode {
        DEBUG,
        PRODUCTION
    }

    var mode: Mode = Mode.DEBUG

    var koinApp: KoinApplication? = null
        private set

    fun initialize(app: Application) {
        koinApp = koinApplication {
            androidContext(app)
            modules(
                listOf(
                    appModule,
                    preferencesModule,
                    apiModule,
                    databaseModule,
                    interactorsModule))
        }
    }

    fun release() {
        koinApp?.close()
        koinApp = null
    }
}