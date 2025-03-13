package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import com.gonzaloracergalan.portfolio.domain.util.UseCaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class SetCurrentResumeIdUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentPerfilUiUseCase")
    }

    private val jsonResumeWrapperRepository: JsonResumeWrapperRepository by inject()

    operator fun invoke(resumeId: Long): Flow<UseCaseResponse> {
        logger.info("invoke")
        return flow {
            emit(UseCaseResponse.Loading)
            try {
                val response = jsonResumeWrapperRepository.setCurrentResumeId(resumeId)
                emit(UseCaseResponse.Success)
            } catch (e: Exception) {
                logger.error("invoke Error al establecer el id del currículum actual", e)
                emit(UseCaseResponse.Error(UseCaseResponse.UseCaseErrorType.UNKNOWN))
            }
        }
    }
}