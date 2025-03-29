package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.TrabajoRepository
import com.gonzaloracergalan.portfolio.data.repository.VoluntariadoRepository
import com.gonzaloracergalan.portfolio.domain.model.Section
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentActiveSectionsUseCase.Companion
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentExperienciaUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentExperienciaUseCase")
    }

    private val trabajoRepository: TrabajoRepository by inject()
    private val voluntariadoRepository: VoluntariadoRepository by inject()

    operator fun invoke(): Flow<> {
        logger.trace("invoke")
    }
}