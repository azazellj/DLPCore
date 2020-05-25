package ua.com.wl.dlp.domain.exeptions.api

/**
 * @author Denis Makovskyi
 */

enum class ApiErrorType {
    SSL_CERTIFICATE,
    RESPONSE_PARSING,
    NETWORK_CONNECTION,
    SERVER_UNREACHABLE,
    INTERNAL_SERVER_ERROR,
}