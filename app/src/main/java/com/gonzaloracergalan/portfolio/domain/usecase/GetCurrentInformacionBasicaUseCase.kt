package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.BasicoRepository
import com.gonzaloracergalan.portfolio.domain.model.InformacionBasica
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentInformacionBasicaUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentInformacionBasicaUseCase")
    }

    private val basicoRepository: BasicoRepository by inject()

    operator fun invoke(): Flow<InformacionBasica> {
        logger.trace("invoke")
        return basicoRepository.currentBasicoWithPerfilesFlow
    }
}