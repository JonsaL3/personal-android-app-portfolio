package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import com.gonzaloracergalan.portfolio.domain.model.Section
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentActiveSectionsUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentActiveSectionsUseCase")
    }

    private val jsonResumeRepository: JsonResumeWrapperRepository by inject()

    operator fun invoke(): Flow<Set<Section>> {
        logger.trace("invoke")
        return jsonResumeRepository.currentSectionsFlow
    }
}