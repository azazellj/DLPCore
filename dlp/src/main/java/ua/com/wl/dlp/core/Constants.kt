package ua.com.wl.dlp.core

/**
 * @author Denis Makovskyi
 */

object Constants {

    const val META_STAGING_URL = "ua.com.wl.dlp.staging.url"
    const val META_PRODUCTION_URL = "ua.com.wl.dlp.production.url"
    const val META_STAGING_APP_IDS = "ua.com.wl.dlp.staging.app_ids"
    const val META_PRODUCTION_APP_IDS = "ua.com.wl.dlp.production.app_ids"

    const val DI_NAMED_URL = "url"
    const val DI_NAMED_APP_IDS = "app_ids"

    const val DI_NAMED_SESSION_OKHTTP = "SESSION_OKHTTP"
    const val DI_NAMED_REFRESH_OKHTTP = "REFRESH_OKHTTP"
    const val DI_NAMED_SESSION_RETROFIT = "SESSION_RETROFIT"
    const val DI_NAMED_REFRESH_RETROFIT = "REFRESH_RETROFIT"
    const val DI_NAMED_API_OKHTTP = "API_OKHTTP"
    const val DI_NAMED_API_RETROFIT = "APi_RETROFIT"

    const val HEADER_APP_ID = "X-APPLICATION-ID"
    const val HEADER_UNAUTHORIZED = "Unauthorized"
    const val HEADER_AUTHORIZATION = "Authorization"

    const val VALUE_PERMIT = "permit"

    const val RECEIVER_ACTION_SOUND_BONUSES = "ua.com.wl.dlp.SOUND_BONUSES"
}