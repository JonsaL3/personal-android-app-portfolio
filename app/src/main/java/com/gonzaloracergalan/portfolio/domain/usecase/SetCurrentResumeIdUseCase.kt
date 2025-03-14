package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import com.gonzaloracergalan.portfolio.domain.util.PortfolioUseCase
import com.gonzaloracergalan.portfolio.domain.util.UseCaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class SetCurrentResumeIdUseCase : PortfolioUseCase() {
    companion object {
        private val logger = LoggerFactory.getLogger("SetCurrentResumeIdUseCase")
    }

    private val jsonResumeWrapperRepository: JsonResumeWrapperRepository by inject()

    suspend operator fun invoke(resumeId: Long): UseCaseResponse {
        logger.info("invoke")
        try {
            val repoResponse = jsonResumeWrapperRepository.setCurrentResumeId(resumeId)
            if (repoResponse.all { it is RepositoryResponse.Success<*> }) {
                UseCaseResponse.Success(resumeId)
            } else {
                map todo procesar respuesta de error
            }
        } catch (e: Exception) {
            logger.warn("invoke", e)
            getUseCaseResponseFromException(e)
        }
    }
}