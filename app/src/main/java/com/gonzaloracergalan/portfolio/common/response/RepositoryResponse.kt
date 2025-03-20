package com.gonzaloracergalan.portfolio.common.response

/**
 * Las respuestas del repo pueden ser de varios tipos y en funcion de ellos devolvemos un dato
 * u otro.
 */
sealed class RepositoryResponse {
    data object Loading : RepositoryResponse()
    data class Success<T>(val data: T?) : RepositoryResponse()
    data class Error(val error: RepositoryErrorType) : RepositoryResponse()

    /**
     * Cuando se produzca un error deberemos especificar el tipo de error, para asi comunicarselo a
     * la capa de dominio.
     */
    enum class RepositoryErrorType {
        // errores relativos a room
        ROOM_RESTRICTION,
        ROOM_IO,
        ROOM_GENERIC,
        ROOM_FAILED_TRANSACTION,
        // todo añadir los errores de retrofit
        UNKNOWN_ERROR
    }
}