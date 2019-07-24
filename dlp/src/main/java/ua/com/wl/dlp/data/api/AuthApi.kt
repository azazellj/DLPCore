package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Headers
import ua.com.wl.dlp.core.Constants

import ua.com.wl.dlp.data.api.requests.auth.AuthenticationRequest
import ua.com.wl.dlp.data.api.requests.auth.CodeRequest
import ua.com.wl.dlp.data.api.requests.auth.SignInRequest
import ua.com.wl.dlp.data.api.requests.auth.SignUpRequest
import ua.com.wl.dlp.data.api.responses.BaseResponse
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthTokenResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse

/**
 * @author Denis Makovskyi
 */

interface AuthApi {

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/registration/check/")
    suspend fun authenticate(@Body request: AuthenticationRequest): Response<DataResponse<AuthenticationResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/auth/token/")
    suspend fun signIn(@Body request: SignInRequest): Response<DataResponse<AuthTokenResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/registration/")
    suspend fun signUp(@Body request: SignUpRequest): Response<DataResponse<AuthTokenResponse>>

    @POST("api/v3/consumer/auth/log-out/")
    suspend fun signOut(): Response<BaseResponse>

    @POST("api/v3/consumer/auth/code/")
    suspend fun retrieveCode(@Body request: CodeRequest): Response<BaseResponse>
}