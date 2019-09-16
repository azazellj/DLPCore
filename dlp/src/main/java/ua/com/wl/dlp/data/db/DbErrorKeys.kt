package ua.com.wl.dlp.data.db

/**
 * @author Denis Makovskyi
 */

object DbErrorKeys {

    const val QUERY_ERROR = "QUERY_ERROR"
    const val SELECT_QUERY_ERROR = "SELECT_QUERY_ERROR"
    const val INSERT_QUERY_ERROR = "INSERT_QUERY_ERROR"
    const val UPDATE_QUERY_ERROR = "UPDATE_QUERY_ERROR"
    const val UPSERT_QUERY_ERROR = "UPSERT_QUERY_ERROR"
    const val DELETE_QUERY_ERROR = "DELETE_QUERY_ERROR"

    const val ENTITY_IS_NOT_EXISTS = "ENTITY_IS_NOT_EXISTS"
    const val ENTITY_IS_NOT_EXISTS_ANYMORE = "ENTITY_IS_NOT_EXISTS_ANYMORE"
}