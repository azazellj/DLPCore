package ua.com.wl.dlp.core

import android.app.Application

import org.koin.dsl.koinApplication
import org.koin.core.KoinApplication
import org.koin.android.ext.koin.androidContext

import ua.com.wl.dlp.core.modules.apiModule
import ua.com.wl.dlp.core.modules.interactorsModule

/**
 * @author Denis Makovskyi
 */

object DLPCore {

    var koinApp: KoinApplication? = null
        private set

    fun initialize(app: Application) {
        koinApp = koinApplication {
            androidContext(app)
            modules(listOf(apiModule, interactorsModule))
        }
    }

    fun release() {
        koinApp?.close()
        koinApp = null
    }
}