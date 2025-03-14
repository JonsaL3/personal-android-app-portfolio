package com.gonzaloracergalan.portfolio.domain.util

import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.slf4j.LoggerFactory

abstract class PortfolioUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("PortfolioUseCase")
    }

    suspend fun <REPO_TYPE, UI_MODEL> runSuspendRepositoryOperation(
        successMapper: (REPO_TYPE) -> UI_MODEL,
        operation: suspend () -> RepositoryResponse,
    ): UseCaseResponse {
        logger.trace("runSuspendOperation single")
        val response = try {
            when (val repoResponse = operation()) {
                is RepositoryResponse.Loading -> return UseCaseResponse.Loading
                is RepositoryResponse.Error -> return UseCaseResponse.Error(
                    getUseCaseResponseErrorTypeFromRepositoryErrorType(repoResponse.error)
                )

                is RepositoryResponse.Success<*> -> processSuccessResponse(
                    successRepoResponse = repoResponse,
                    successMapper = successMapper
                )
            }
        } catch (e: Exception) {
            logger.warn("runSuspendOperation single", e)
            getUseCaseResponseFromException(e)
        }
        logger.trace("runSuspendOperation single response: {}", response)
        return response
    }

    /**
     * Extensión que mapea un Flow<RepositoryResponse> a un Flow<UseCaseResponse>
     * usando un mapper que transforma el dato del repositorio (D) en el dato de UI (R).
     */
    protected fun <REPO_TYPE, UI_MODEL> Flow<RepositoryResponse>.toUseCaseFlow(
        successMapper: (REPO_TYPE) -> UI_MODEL
    ) = this.map { repositoryResponse ->
        mapRepositoryResponseToUseCaseResponse(repositoryResponse, successMapper)
    }.catch { getUseCaseResponseFromException(it) }

    /**
     * Función que mapea la respuesta del repositorio a la respuesta del use case.
     * Se espera que el mapper transforme el dato obtenido (D) en el dato de UI (R).
     */
    protected fun <REPO_TYPE, UI_MODEL> mapRepositoryResponseToUseCaseResponse(
        repositoryResponse: RepositoryResponse,
        successMapper: (REPO_TYPE) -> UI_MODEL
    ): UseCaseResponse {
        logger.trace("mapRepositoryResponseToUseCaseResponse")
        return when (repositoryResponse) {
            is RepositoryResponse.Loading -> UseCaseResponse.Loading
            is RepositoryResponse.Error -> UseCaseResponse.Error(
                getUseCaseResponseErrorTypeFromRepositoryErrorType(repositoryResponse.error)
            )

            is RepositoryResponse.Success<*> -> processSuccessResponse(
                successRepoResponse = repositoryResponse,
                successMapper = successMapper
            )
        }
    }

    /**
     * Función que mapea un throwable a una respuesta del use case.
     */
    protected fun getUseCaseResponseFromException(
        e: Throwable
    ): UseCaseResponse {
        logger.trace("getUseCaseResponseFromException exception: {}", e.message)
        val response = when (e) {
            // todo tratar algun caso que pueda darse.
            else -> UseCaseResponse.Error(UseCaseResponse.UseCaseErrorType.UNKNOWN_ERROR)
        }
        logger.warn("getUseCaseResponseFromException response: {}", response)
        return response
    }

    /**
     * Función que mapea un tipo de error del repositorio a un tipo de error del use case.
     */
    protected fun getUseCaseResponseErrorTypeFromRepositoryErrorType(
        repositoryErrorType: RepositoryResponse.RepositoryErrorType
    ): UseCaseResponse.UseCaseErrorType {
        logger.trace("getUseCaseResponseErrorTypeFromRepositoryErrorType")
        return when (repositoryErrorType) {
            RepositoryResponse.RepositoryErrorType.ROOM_IO,
            RepositoryResponse.RepositoryErrorType.ROOM_FAILED_TRANSACTION,
            RepositoryResponse.RepositoryErrorType.ROOM_GENERIC -> UseCaseResponse.UseCaseErrorType.INTERNAL_DATABASE_ERROR

            RepositoryResponse.RepositoryErrorType.ROOM_RESTRICTION -> UseCaseResponse.UseCaseErrorType.INTERNAL_DATABASE_RESTRICTION
            else -> UseCaseResponse.UseCaseErrorType.UNKNOWN_ERROR
        }
    }

    protected fun <REPO_TYPE, UI_MODEL> processSuccessResponse(
        successRepoResponse: RepositoryResponse.Success<*>,
        successMapper: (REPO_TYPE) -> UI_MODEL
    ): UseCaseResponse {
        // Intentamos hacer un cast seguro del dato al tipo esperado D.
        val uiType = (successRepoResponse.data as? REPO_TYPE)?.let(successMapper)
        val useCaseResponse = uiType?.let { mUiType ->
            // Si se trata de una colección, comprobamos que no esté vacía.
            if (mUiType is Collection<*> && mUiType.isEmpty()) {
                logger.info("processSuccessResponse: Lista de datos vacía")
                UseCaseResponse.NotData
            } else {
                logger.info("processSuccessResponse: Datos no nulos, mapeando a UI...")
                UseCaseResponse.Success(mUiType)
            }
        } ?: UseCaseResponse.NotData
        logger.trace("processSuccessResponse response: {}", useCaseResponse)
        return useCaseResponse
    }
}