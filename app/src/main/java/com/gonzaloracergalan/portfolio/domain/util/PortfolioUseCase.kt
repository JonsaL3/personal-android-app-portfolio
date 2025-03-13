package com.gonzaloracergalan.portfolio.domain.util

import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.KoinComponent
import org.slf4j.LoggerFactory

abstract class PortfolioUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("PortfolioUseCase")
    }

    /**
     * Mapeamos un flow cualquiera a una respuesta del useCase con los errores y casos necesarios
     * para UI tratados
     */
    protected fun <T> Flow<RepositoryResponse>.toUseCaseFlow(
        successTypeMapper: (T) -> UseCaseResponse.Success<*>
    ) = this.onStart { UseCaseResponse.Loading }
        .map { mapRepositoryResponseToUseCaseResponse(it) }
        .catch { emit(UseCaseResponse.Error(UseCaseResponse.UseCaseErrorType.UNKNOWN_ERROR)) }

    protected fun <T> mapRepositoryResponseToUseCaseResponse(
        repositoryResponse: RepositoryResponse,
        successTypeMapper: (T) -> UseCaseResponse.Success<*>
    ): UseCaseResponse {
        logger.trace("mapRepositoryResponseToUseCaseResponse")
        return when (repositoryResponse) {
            is RepositoryResponse.Loading -> UseCaseResponse.Loading
            is RepositoryResponse.Success<*> -> {
                when (repositoryResponse.data) {
                    null -> UseCaseResponse.NotData
                    is Collection<*> -> {
                        // Garantizamos que siempre que nos llegue una colección esta tenga datos:
                        if (repositoryResponse.data.isEmpty()) {
                            logger.info("mapRepositoryResponseToUseCaseResponse: Lista de datos vacía")
                            UseCaseResponse.NotData
                        } else {
                            logger.info("mapRepositoryResponseToUseCaseResponse: Lista de datos no vacía, mapeando a UI...")
                            val toConvert = repositoryResponse.data as? T
                            toConvert?.let {
                                UseCaseResponse.Success(successTypeMapper(toConvert))
                            } ?: run {
                                UseCaseResponse.Error(UseCaseResponse.UseCaseErrorType.UNKNOWN_ERROR)
                                esto es un gran dilema que hay que resolver, realmente es posible?¿?¿
                            }
                        }
                    }

                    else -> {
                        logger.info("mapRepositoryResponseToUseCaseResponse: Datos no nulos, mapeando a UI...")
                        UseCaseResponse.Success(successTypeMapper(repositoryResponse.data))
                    }
                }
            }

            is RepositoryResponse.Error -> UseCaseResponse.Error(
                getUseCaseResponseErrorTypeFromRepositoryErrorType(
                    repositoryErrorType = repositoryResponse.error
                )
            )
        }
    }

    protected fun getUseCaseResponseErrorTypeFromRepositoryErrorType(
        repositoryErrorType: RepositoryResponse.RepositoryErrorType
    ): UseCaseResponse.UseCaseErrorType {
        return when (repositoryErrorType) {
            RepositoryResponse.RepositoryErrorType.ROOM_IO,
            RepositoryResponse.RepositoryErrorType.ROOM_GENERIC -> UseCaseResponse.UseCaseErrorType.INTERNAL_DATABASE_ERROR

            RepositoryResponse.RepositoryErrorType.ROOM_RESTRICTION -> UseCaseResponse.UseCaseErrorType.INTERNAL_DATABASE_RESTRICTION
            else -> UseCaseResponse.UseCaseErrorType.UNKNOWN_ERROR
        }
    }

}