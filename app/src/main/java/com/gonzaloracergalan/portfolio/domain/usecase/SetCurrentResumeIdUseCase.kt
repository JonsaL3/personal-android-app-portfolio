package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import com.gonzaloracergalan.portfolio.domain.util.PortfolioUseCase
import com.gonzaloracergalan.portfolio.domain.util.UseCaseResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class SetCurrentResumeIdUseCase : PortfolioUseCase() {
    companion object {
        private val logger = LoggerFactory.getLogger("SetCurrentResumeIdUseCase")
    }

    private val jsonResumeWrapperRepository: JsonResumeWrapperRepository by inject()

    // este mapper es un poco absurdo en este caso pero es lo que espera nuestra
    // runSuspendRepositoryOperation generica...
    private val longToLong: (
        Long,
    ) -> Long = { resumeId: Long ->
        resumeId
    }

    suspend operator fun invoke(resumeId: Long): UseCaseResponse = runSuspendRepositoryOperation(
        successMapper = longToLong
    ) {
        logger.trace("invoke")
        jsonResumeWrapperRepository.setCurrentResumeId(resumeId)
    }
}