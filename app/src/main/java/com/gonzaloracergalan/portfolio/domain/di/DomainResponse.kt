package com.gonzaloracergalan.portfolio.domain.di

/**
 * Las respuestas del repo pueden ser de varios tipos y en funcion de ellos devolvemos un dato
 * u otro.
 */
sealed class DomainResponse {
    data object Loading : DomainResponse()
    data class Success<T>(val data: T) : DomainResponse()
    data class Error(val error: DomainErrorType) : DomainResponse()

    /**
     * Cuando se produzca un error deberemos especificar el tipo de error, para asi comunicarselo a
     * la capa de dominio.
     */
    enum class DomainErrorType {
        NOT_FOUND_DATA,
        BAD_DATA,
    }
}