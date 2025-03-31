package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.TrabajoRepository
import com.gonzaloracergalan.portfolio.data.repository.VoluntariadoRepository
import com.gonzaloracergalan.portfolio.domain.model.Experiencia
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentExperienciaUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentExperienciaUseCase")
    }

    private val trabajoRepository: TrabajoRepository by inject()
    private val voluntariadoRepository: VoluntariadoRepository by inject()

    operator fun invoke(): Flow<Experiencia> = combine(
        trabajoRepository.currentTrabajosFlow,
        voluntariadoRepository.currentVoluntariadosFlow,
    ) { trabajos, voluntariados ->
        logger.debug("trabajos: {}", trabajos)
        logger.debug("voluntariados: {}", voluntariados)
        Experiencia(
            trabajos = trabajos,
            voluntariados = voluntariados,
        )
    }
}