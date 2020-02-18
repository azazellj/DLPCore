package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.requests.auth.*
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.auth.TokenResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse
import ua.com.wl.dlp.data.api.responses.auth.SignResponse
import ua.com.wl.dlp.data.api.responses.models.auth.CardsStatus
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface AuthInteractor {

    suspend fun verification(): Result<TokenResponse>

    suspend fun refreshToken(): Result<TokenResponse>

    suspend fun authentication(request: AuthenticationRequest): Result<AuthenticationResponse>

    suspend fun signIn(request: SignInRequest, appVersion: String? = null): Result<SignResponse>

    suspend fun cardsStatus(request: CardsStatusRequest): Result<CardsStatus>

    suspend fun signUp(request: SignUpRequest, appVersion: String? = null): Result<SignResponse>

    suspend fun signOut(): Result<Boolean>

    suspend fun requestSmsCode(request: SmsCodeRequest): Result<Boolean>

    suspend fun restorePassword(request: SmsCodeRequest): Result<Boolean>

    suspend fun cities(): Result<PagedResponse<City>>
}