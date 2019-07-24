package ua.com.wl.dlp.core.network

import okhttp3.Response
import okhttp3.Interceptor

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.utils.hasHeader

/**
 * @author Denis Makovskyi
 */

class AuthInterceptor(private val appId: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request().let {
            chain.proceed(it.newBuilder().apply {
                addHeader(Constants.HEADER_APP_ID, appId)
                if (it.hasHeader(Constants.HEADER_UNAUTHORIZED, Constants.VALUE_PERMIT)) {
                    removeHeader(Constants.HEADER_UNAUTHORIZED)

                } else {
                    if (true) {
                        //TODO: read token from preferences
                        addHeader(Constants.HEADER_AUTHORIZATION, "Token${0}")

                    } else {
                        throw RuntimeException("Authorization token required in private api was not found")
                    }
                }
            }.build())
        }
}