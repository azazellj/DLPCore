package ua.com.wl.dlp.domain.interactors

import ua.com.wl.dlp.data.api.requests.consumer.profile.ProfileRequest
import ua.com.wl.dlp.data.api.responses.PaginationResponse
import ua.com.wl.dlp.data.api.responses.consumer.history.TransactionResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.consumer.referral.ReferralActivationResponse
import ua.com.wl.dlp.data.api.responses.consumer.shop.ShopsResponse
import ua.com.wl.dlp.domain.Result

/**
 * @author Denis Makovskyi
 */

interface ConsumerInteractor {

    suspend fun getProfile(): Result<ProfileResponse>

    suspend fun updateProfile(profile: ProfileRequest): Result<ProfileResponse>

    suspend fun activateReferralCode(code: String): Result<ReferralActivationResponse>

    suspend fun getCityShops(): Result<PaginationResponse<ShopsResponse>>

    suspend fun loadTransactionsHistory(): Result<PaginationResponse<TransactionResponse>>
}