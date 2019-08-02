package ua.com.wl.dlp.domain.interactors.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ua.com.wl.dlp.data.api.AuthApi
import ua.com.wl.dlp.data.api.ResponseType
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.auth.*
import ua.com.wl.dlp.data.api.responses.PaginationResponse
import ua.com.wl.dlp.data.api.responses.auth.TokenResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse
import ua.com.wl.dlp.data.api.responses.auth.SignResponse
import ua.com.wl.dlp.data.api.responses.models.auth.CardsStatus
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences
import ua.com.wl.dlp.data.prefereces.CorePreferences
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.auth.AuthException
import ua.com.wl.dlp.domain.interactors.AuthInteractor

/**
 * @author Denis Makovskyi
 */

class AuthInteractorImpl(
    private val corePreferences: CorePreferences,
    private val consumerPreferences: ConsumerPreferences,
    private val api: AuthApi,
    errorsMapper: ErrorsMapper) : AuthInteractor, UseCase(errorsMapper) {

    override suspend fun verification(): Result<TokenResponse> =
        callApi(
            call = { api.verification(TokenRequest(corePreferences.corePrefs.authToken)) },
            errorClass = AuthException::class.java
        ).fMap {
            it?.payload
        }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    corePreferences.corePrefs = corePreferences.corePrefs.copy(authToken = it.data.token)
                }
            }
        }

    override suspend fun refreshToken(): Result<TokenResponse> =
        callApi(
            call = { api.refreshToken(TokenRequest(corePreferences.corePrefs.refreshToken)) },
            errorClass = AuthException::class.java
        ).fMap {
            it?.payload
        }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    corePreferences.corePrefs = corePreferences.corePrefs.copy(authToken = it.data.token)
                }
            }
        }

    override suspend fun authentication(phone: String, sendSms: Boolean): Result<AuthenticationResponse> =
        callApi(
            call = { api.authentication(AuthenticationRequest(sendSms, phone)) },
            errorClass = AuthException::class.java).fMap { it?.payload }

    override suspend fun signIn(phone: String, password: String): Result<SignResponse> =
        callApi(
            call = { api.signIn(SignInRequest(phone, password)) },
            errorClass = AuthException::class.java
        ).fMap {
            it?.payload
        }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    corePreferences.corePrefs = corePreferences.corePrefs.copy(
                        authToken = it.data.token, refreshToken = it.data.refreshToken)
                }
            }
        }

    override suspend fun cardsStatus(phone: String, password: String): Result<CardsStatus> =
        callApi(
            call = { api.cardsStatus(CardsStatusRequest(phone, password)) },
            errorClass = AuthException::class.java).fMap { it?.payload?.cardsStatus }

    override suspend fun signUp(city: Int, phone: String, password: String, barcode: String?): Result<SignResponse> =
        callApi(
            call = { api.signUp(SignUpRequest(city, phone, password, barcode)) },
            errorClass = AuthException::class.java
        ).fMap {
            it?.payload
        }.also {
            if (it is Result.Success && it.data != null) {
                withContext(Dispatchers.IO) {
                    corePreferences.corePrefs = corePreferences.corePrefs.copy(
                        authToken = it.data.token, refreshToken = it.data.refreshToken)
                }
            }
        }

    override suspend fun signOut(): Result<Boolean> =
        callApi(
            call = { api.signOut() },
            errorClass = AuthException::class.java
        ).fMap {
            it?.type.equals(ResponseType.OK)
        }.also {
            withContext(Dispatchers.IO) {
                corePreferences.removeCorePrefs()
                consumerPreferences.removeProfilePrefs()
            }
        }

    override suspend fun requestSmsCode(phone: String): Result<Boolean> =
        callApi(
            call = { api.requestSmsCode(SmsCodeRequest(phone)) },
            errorClass = AuthException::class.java).fMap { it?.type.equals(ResponseType.OK) }

    override suspend fun cities(): Result<PaginationResponse<City>> =
        callApi(call = { api.cities() }).fMap { it?.payload }
}