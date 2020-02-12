package ua.com.wl.dlp.domain.interactors

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

    suspend fun authentication(phone: String, sendSms: Boolean = true): Result<AuthenticationResponse>

    suspend fun signIn(phone: String, password: String, appVersion: String? = null): Result<SignResponse>

    suspend fun cardsStatus(phone: String, password: String): Result<CardsStatus>

    suspend fun signUp(
        city: Int,
        phone: String,
        password: String,
        barcode: String? = null,
        appVersion: String? = null
    ): Result<SignResponse>

    suspend fun signOut(): Result<Boolean>

    suspend fun requestSmsCode(phone: String): Result<Boolean>

    suspend fun restorePassword(phone: String): Result<Boolean>

    suspend fun cities(): Result<PagedResponse<City>>
}