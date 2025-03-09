package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.BasicoRepository
import com.gonzaloracergalan.portfolio.data.repository.PerfilRepository
import com.gonzaloracergalan.portfolio.ui.model.PerfilUI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetPerfilUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("GetPerfilUseCase")
    }

    private val basicoRepository: BasicoRepository by inject()
    private val perfilRepository: PerfilRepository by inject()

    private val perfilStateFlow = basicoRepository.getBasicoByIdFlow()

    suspend operator fun invoke(resumeOwnerId: Long): PerfilUI? {
        try {

        } catch (e: Exception) {

        }
    }
}