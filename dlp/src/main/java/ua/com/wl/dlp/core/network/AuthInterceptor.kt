package ua.com.wl.dlp.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.SessionApi
import ua.com.wl.dlp.data.api.requests.auth.TokenRequest
import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.utils.hasHeader
import ua.com.wl.dlp.utils.toJwt
import java.net.HttpURLConnection

/**
 * @author Denis Makovskyi
 */

class AuthInterceptor constructor(
    val appIds: List<String>,
    private val corePreferences: CorePreferences,
    retrofit: Retrofit
) : Interceptor {

    var appId: String = appIds[0]

    private val api: SessionApi = retrofit.create(SessionApi::class.java)

    private fun wrapRequest(request: Request, token: String?): Request {
        return request.newBuilder().apply {
            header(Constants.HEADER_APP_ID, appId)
            if (request.hasHeader(Constants.HEADER_UNAUTHORIZED, Constants.VALUE_PERMIT)) {
                removeHeader(Constants.HEADER_UNAUTHORIZED)
            } else {
                header(Constants.HEADER_AUTHORIZATION, token.toJwt())
            }
        }.build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = wrapRequest(chain.request(), corePreferences.authPrefs.authToken)
        val originalResponse = chain.proceed(originalRequest)

        return when (originalResponse.code) {
            HttpURLConnection.HTTP_UNAUTHORIZED, HttpURLConnection.HTTP_FORBIDDEN -> {
                return runBlocking(Dispatchers.IO) {
                    val request = TokenRequest(corePreferences.authPrefs.refreshToken)
                    val response = api.refreshToken(request, appId)
                    if (!response.isSuccessful) {
                        return@runBlocking originalResponse
                    }
                    val token = response.body()?.payload?.token
                    corePreferences.authPrefs = corePreferences.authPrefs.copy(authToken = token)

                    chain.proceed(wrapRequest(originalRequest, token))
                }
            }
            else -> originalResponse
        }
    }
}