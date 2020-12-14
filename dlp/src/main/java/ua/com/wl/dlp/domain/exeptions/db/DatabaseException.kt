package ua.com.wl.dlp.domain.exeptions.db

import android.content.Context

import ua.com.wl.dlp.common.R
import ua.com.wl.dlp.data.db.DbErrorKeys
import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException

/**
 * @author Denis Makovskyi
 */

open class DatabaseException(
    message: String? = null,
    cause: Throwable? = null
) : CoreRuntimeException(message, cause) {

    override fun getLocalizedMessage(context: Context): String = when(message) {
        DbErrorKeys.ENTITY_IS_NOT_EXISTS -> context.getString(R.string.dlp_error_db_entity_is_not_exists)
        DbErrorKeys.ENTITY_IS_NOT_EXISTS_ANYMORE -> context.getString(R.string.dlp_error_db_entity_is_not_exists_anymore)
        else -> super.getLocalizedMessage(context)
    }
}