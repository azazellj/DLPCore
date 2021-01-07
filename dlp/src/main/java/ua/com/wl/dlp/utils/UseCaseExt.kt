package ua.com.wl.dlp.utils

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.api.responses.BaseResponse
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.domain.Result
import ua.com.wl.dlp.domain.exeptions.api.ApiException

fun <T, O> Result<Optional<DataResponse<T>>>.fromDataResponse(
    successMapper: (DataResponse<T>) -> O,
    errorMapper: () -> Exception = { ApiException() }
): Result<O> {
    return flatMap { dataResponseOpt ->
        dataResponseOpt.ifPresentOrDefault(
            { Result.Success(successMapper.invoke(it)) },
            { Result.Failure(errorMapper.invoke()) })
    }
}

fun <T> Result<Optional<DataResponse<T>>>.fromDataResponse(
    errorMapper: () -> Exception = { ApiException() }
): Result<T> {
    return fromDataResponse(successMapper = { it.payload }, errorMapper)
}

fun <T, O> Result<Optional<T>>.fromResponse(
    successMapper: (T) -> O,
    errorMapper: () -> Exception = { ApiException() }
): Result<O> {
    return flatMap { dataResponseOpt ->
        dataResponseOpt.ifPresentOrDefault(
            { Result.Success(successMapper.invoke(it)) },
            { Result.Failure(errorMapper.invoke()) })
    }
}

fun <T> Result<Optional<T>>.fromResponse(
    errorMapper: () -> Exception = { ApiException() }
): Result<T> {
    return fromResponse(successMapper = { it }, errorMapper)
}

fun Result<Optional<BaseResponse>>.mapIsSuccessfully(): Result<Boolean> {
    return map { it.getUnsafe()?.isSuccessfully() ?: false }
}