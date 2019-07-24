package ua.com.wl.dlp.core

import org.koin.core.Koin
import org.koin.core.KoinComponent

/**
 * @author Denis Makovskyi
 */

interface DLPCoreComponent : KoinComponent {

    override fun getKoin(): Koin =
        DLPCore.koinApp?.koin
            ?: throw IllegalStateException("DLPCore is not initialized, please initialize core with your application context")
}