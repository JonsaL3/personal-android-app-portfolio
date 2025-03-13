package com.gonzaloracergalan.portfolio.domain.util

/**
 * Las respuestas del repo pueden ser de varios tipos y en funcion de ellos devolvemos un dato
 * u otro.
 */
sealed class UseCaseResponse {
    data object Loading : UseCaseResponse()
    data object NotData : UseCaseResponse()
    data class Success<T>(val data: T) : UseCaseResponse()
    data class Error(val error: UseCaseErrorType) : UseCaseResponse()

    /**
     * Cuando se produzca un error deberemos especificar el tipo de error, para asi comunicarselo a
     * la capa de dominio.
     */
    enum class UseCaseErrorType {
        BAD_REQUEST_PARAMS,
        CAST_BETWEEN_UI_TO_DATA_ERROR,
        INTERNAL_DATABASE_ERROR,
        INTERNAL_DATABASE_RESTRICTION,
        // todo añadir los errores remotos y los que puedan faltar
        UNKNOWN_ERROR
    }
}