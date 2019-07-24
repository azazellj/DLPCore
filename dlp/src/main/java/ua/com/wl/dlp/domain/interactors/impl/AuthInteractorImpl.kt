package ua.com.wl.dlp.domain.interactors.impl

import ua.com.wl.dlp.data.api.AuthApi
import ua.com.wl.dlp.data.api.ResponseType
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.data.api.requests.auth.AuthenticationRequest
import ua.com.wl.dlp.data.api.requests.auth.CodeRequest
import ua.com.wl.dlp.data.api.requests.auth.SignInRequest
import ua.com.wl.dlp.data.api.requests.auth.SignUpRequest
import ua.com.wl.dlp.data.api.responses.auth.AuthTokenResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.UseCase
import ua.com.wl.dlp.domain.exeptions.auth.AuthException
import ua.com.wl.dlp.domain.interactors.AuthInteractor

class AuthInteractorImpl(
    val api: AuthApi, errorsMapper: ErrorsMapper) : AuthInteractor, UseCase(errorsMapper) {

    override suspend fun authenticate(phone: String, sendSms: Boolean): Result<AuthenticationResponse> =
        callApi(
            call = { api.authenticate(AuthenticationRequest(sendSms, phone)) },
            errorClass = AuthException::class.java).fMap { it?.data }

    override suspend fun signIn(phone: String, password: String): Result<AuthTokenResponse> =
        callApi(
            call = { api.signIn(SignInRequest(phone, password)) },
            errorClass = AuthException::class.java).fMap { it?.data }

    override suspend fun signUp(city: Int, phone: String, password: String, barcode: String?): Result<AuthTokenResponse> =
        callApi(
            call = { api.signUp(SignUpRequest(city, phone, password, barcode)) },
            errorClass = AuthException::class.java).fMap { it?.data }

    override suspend fun signOut(): Result<Boolean> =
        callApi(
            call = { api.signOut() },
            errorClass = AuthException::class.java).fMap { it?.type.equals(ResponseType.OK) }

    override suspend fun retrieveCode(phone: String): Result<Boolean> =
        callApi(
            call = { api.retrieveCode(CodeRequest(phone)) },
            errorClass = AuthException::class.java).fMap { it?.type.equals(ResponseType.OK) }
}