package ua.com.wl.dlp.domain.exeptions.db

import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException

/**
 * @author Denis Makovskyi
 */

open class DatabaseException(message: String? = null, cause: Throwable? = null) : CoreRuntimeException(message, cause)