package ua.com.wl.dlp.core.network

import java.net.HttpURLConnection

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

import retrofit2.Retrofit

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.SessionApi
import ua.com.wl.dlp.data.api.requests.auth.TokenRequest
import ua.com.wl.dlp.data.events.factory.CoreBusEventsFactory
import ua.com.wl.dlp.data.events.session.SessionBusEvent
import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.utils.toJwt

/**
 * @author Denis Makovskyi
 */

class SessionAuthenticator constructor(
    retrofit: Retrofit,
    private val appId: String,
    private val corePreferences: CorePreferences
) : Authenticator {

    private val api: SessionApi = retrofit.create(SessionApi::class.java)

    override fun authenticate(route: Route?, response: Response): Request? =
        when(response.code) {
            HttpURLConnection.HTTP_UNAUTHORIZED, HttpURLConnection.HTTP_FORBIDDEN -> {
                runBlocking {
                    val dataResponse = api.refreshToken(TokenRequest(corePreferences.authPrefs.refreshToken))
                    if (dataResponse.isSuccessful) {
                        val tokenResponse = dataResponse.body()?.payload
                        if (tokenResponse != null) {
                            withContext(Dispatchers.IO) {
                                corePreferences.authPrefs = corePreferences.authPrefs.copy(authToken = tokenResponse.token)
                            }
                            response.request.newBuilder()
                                .header(Constants.HEADER_APP_ID, appId)
                                .header(Constants.HEADER_AUTHORIZATION, corePreferences.authPrefs.authToken.toJwt())
                                .build()

                        } else {
                            CoreBusEventsFactory.sessionExpired(SessionBusEvent.FallbackType.TOKEN_EXPIRED)
                        }
                    }
                    null
                }
            }
            else -> null
        }
}
