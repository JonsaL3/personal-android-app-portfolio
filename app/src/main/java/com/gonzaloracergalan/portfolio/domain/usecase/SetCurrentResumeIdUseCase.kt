package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class SetCurrentResumeIdUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("SetCurrentResumeIdUseCase")
    }

    private val jsonResumeWrapperRepository: JsonResumeWrapperRepository by inject()

    suspend operator fun invoke(resumeId: Long): Boolean {
        logger.trace("invoke")
        return try {
            jsonResumeWrapperRepository.setCurrentResumeId(resumeId)
            true
        } catch (e: Exception) {
            logger.error("invoke Error setting current resume id", e)
            false
        }
    }
}