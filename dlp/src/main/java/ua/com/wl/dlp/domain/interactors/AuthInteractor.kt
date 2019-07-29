package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.responses.PaginationResponse
import ua.com.wl.dlp.data.api.responses.auth.TokenResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface AuthInteractor {

    suspend fun verification(): Result<TokenResponse>

    suspend fun refreshToken(): Result<TokenResponse>

    suspend fun authentication(phone: String, sendSms: Boolean = true): Result<AuthenticationResponse>

    suspend fun signIn(phone: String, password: String): Result<TokenResponse>

    suspend fun signUp(city: Int, phone: String, password: String, barcode: String? = null): Result<TokenResponse>

    suspend fun signOut(): Result<Boolean>

    suspend fun retrieveCode(phone: String): Result<Boolean>

    suspend fun cities(): Result<PaginationResponse<City>>
}