package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.common.response.UseCaseResponse
import com.gonzaloracergalan.portfolio.data.db.calculated.ActiveResumeSection
import com.gonzaloracergalan.portfolio.data.db.calculated.ActiveResumeSectionsCalculated
import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import com.gonzaloracergalan.portfolio.domain.util.PortfolioUseCase
import com.gonzaloracergalan.portfolio.ui.model.ActiveResumeSectionUi
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentActiveSectionsUseCase : PortfolioUseCase() {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentActiveSectionsUseCase")
    }

    private val jsonResumeRepository: JsonResumeWrapperRepository by inject()

    private val dataToUiParser: (
        ActiveResumeSectionsCalculated,
    ) -> Set<ActiveResumeSectionUi> = { activeResumeSections: ActiveResumeSectionsCalculated ->
        activeResumeSections.activeResumeSections.map {
            when (it) {
                ActiveResumeSection.BASICO -> ActiveResumeSectionUi.INFORMACION_GENERAL
                ActiveResumeSection.TRABAJO,
                ActiveResumeSection.VOLUNTARIADO -> ActiveResumeSectionUi.EXPERIENCIA

                ActiveResumeSection.EDUCACION -> ActiveResumeSectionUi.ESTUDIOS
                ActiveResumeSection.PREMIO,
                ActiveResumeSection.CERTIFICADOS,
                ActiveResumeSection.REFERENCIA -> ActiveResumeSectionUi.PREMIOS_CERTIFICADOS

                ActiveResumeSection.PUBLICACION -> ActiveResumeSectionUi.PUBLICACIONES
                ActiveResumeSection.PROYECTO -> ActiveResumeSectionUi.PROYECTOS
                ActiveResumeSection.HABILIDAD,
                ActiveResumeSection.IDIOMA,
                ActiveResumeSection.INTERES -> ActiveResumeSectionUi.MAS_SOBRE_MI
            }
        }.toSet()
    }

    operator fun invoke(): Flow<UseCaseResponse> {
        logger.trace("invoke")
        return jsonResumeRepository.currentActiveResumeSectionsFlow.toUseCaseFlow(
            successMapper = dataToUiParser
        )
    }
}