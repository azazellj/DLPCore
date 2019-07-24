package ua.com.wl.dlp.domain.exeptions

import android.content.Context

import ua.com.wl.dlp.R

open class ApiRuntimeException(message: String? = null) : ApiException(message) {

    override fun getLocalizedMessage(context: Context): String = context.getString(R.string.dlp_error_api_runtime)
}