package ua.com.wl.dlp.core.network

import java.net.HttpURLConnection

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers

import okhttp3.Route
import okhttp3.Request
import okhttp3.Response
import okhttp3.Authenticator

import retrofit2.Retrofit

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.SessionApi
import ua.com.wl.dlp.data.api.requests.auth.TokenRequest
import ua.com.wl.dlp.data.events.session.SessionBusEvent
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.utils.toJwt

/**
 * @author Denis Makovskyi
 */

class SessionAuthenticator constructor(
    retrofit: Retrofit,
    private val authInterceptor: AuthInterceptor,
    private val corePreferences: CorePreferences
) : Authenticator {

    private val api: SessionApi = retrofit.create(SessionApi::class.java)

    override fun authenticate(route: Route?, response: Response): Request? {
        return when(response.code) {
            HttpURLConnection.HTTP_UNAUTHORIZED, HttpURLConnection.HTTP_FORBIDDEN -> {
                runBlocking(Dispatchers.IO) {
                    fun sessionExpired(): Request? {
                        CoreBusEventsFactory.sessionExpired(
                            SessionBusEvent.FallbackType.TOKEN_EXPIRED
                        )
                        return null
                    }

                    val dataResponse = try {
                        api.refreshToken(
                            TokenRequest(corePreferences.authPrefs.refreshToken), authInterceptor.appId
                        )
                    } catch (e: Exception) {
                        return@runBlocking sessionExpired()
                    }

                    if (!dataResponse.isSuccessful) {
                        return@runBlocking sessionExpired()
                    }

                    val tokenResponse = dataResponse.body()?.payload
                        ?: return@runBlocking sessionExpired()

                    corePreferences.authPrefs = corePreferences.authPrefs.copy(authToken = tokenResponse.token)
                    return@runBlocking response.request.newBuilder()
                        .header(Constants.HEADER_APP_ID, authInterceptor.appId)
                        .header(Constants.HEADER_AUTHORIZATION, corePreferences.authPrefs.authToken.toJwt())
                        .build()
                }
            }
            else -> null
        }
    }
}
