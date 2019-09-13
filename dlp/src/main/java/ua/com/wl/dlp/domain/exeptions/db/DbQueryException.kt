package ua.com.wl.dlp.domain.exeptions.db

import android.content.Context

import ua.com.wl.dlp.R
import ua.com.wl.dlp.data.db.DbErrorKeys

/**
 * @author Denis Makovskyi
 */

class DbQueryException(message: String): DatabaseException(message) {

    override fun getLocalizedMessage(context: Context): String = when(message) {
        DbErrorKeys.SELECT_QUERY_ERROR -> context.getString(R.string.dlp_error_db_select)
        DbErrorKeys.INSERT_QUERY_ERROR -> context.getString(R.string.dlp_error_db_insert)
        DbErrorKeys.UPDATE_QUERY_ERROR -> context.getString(R.string.dlp_error_db_update)
        DbErrorKeys.UPSERT_QUERY_ERROR -> context.getString(R.string.dlp_error_db_upsert)
        DbErrorKeys.DELETE_QUERY_ERROR -> context.getString(R.string.dlp_error_db_delete)
        else -> super.getLocalizedMessage(context)
    }
}