package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.responses.auth.AuthTokenResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface AuthInteractor {

    suspend fun authenticate(phone: String, sendSms: Boolean = true): Result<AuthenticationResponse>

    suspend fun signIn(phone: String, password: String): Result<AuthTokenResponse>

    suspend fun signUp(city: Int, phone: String, password: String, barcode: String? = null): Result<AuthTokenResponse>

    suspend fun signOut(): Result<Boolean>

    suspend fun retrieveCode(phone: String): Result<Boolean>
}