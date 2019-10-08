package ua.com.wl.dlp.core.network

import okhttp3.Response
import okhttp3.Interceptor

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.utils.hasHeader
import ua.com.wl.dlp.utils.toJwt

/**
 * @author Denis Makovskyi
 */

class AuthInterceptor(
    private val appId: String,
    private val corePreferences: CorePreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request().let { request ->
            chain.proceed(request.newBuilder().apply {
                header(Constants.HEADER_APP_ID, appId)
                if (request.hasHeader(Constants.HEADER_UNAUTHORIZED, Constants.VALUE_PERMIT)) {
                    removeHeader(Constants.HEADER_UNAUTHORIZED)

                } else {
                    val token = corePreferences.authPrefs.authToken
                    if (token != null) {
                        header(Constants.HEADER_AUTHORIZATION, token.toJwt())
                    } else {
                        throw RuntimeException("Authorization token required in private api was not found")
                    }
                }
            }.build())
        }
}