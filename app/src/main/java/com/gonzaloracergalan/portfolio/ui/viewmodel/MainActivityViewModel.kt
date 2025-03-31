package com.gonzaloracergalan.portfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzaloracergalan.portfolio.domain.model.Section
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentActiveSectionsUseCase
import com.gonzaloracergalan.portfolio.ui.model.MainActivityModel
import com.gonzaloracergalan.portfolio.ui.state.MainActivityState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class MainActivityViewModel : ViewModel(), KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("MainActivityViewModel")
    }

    private val getCurrentActiveSectionsState: GetCurrentActiveSectionsUseCase by inject()

    val mainActivityState: StateFlow<MainActivityState> = getCurrentActiveSectionsState()
        .map { sectionSet ->
            logger.trace("mainActivityState: {}", sectionSet)
            val sections = sectionSet.map {
                when (it) {
                    Section.BASICO -> MainActivityModel.Section.INFORMACION_GENERAL
                    Section.TRABAJO,
                    Section.VOLUNTARIADO -> MainActivityModel.Section.EXPERIENCIA
                    Section.EDUCACION -> MainActivityModel.Section.ESTUDIOS
                    Section.PREMIO,
                    Section.REFERENCIA,
                    Section.CERTIFICADOS -> MainActivityModel.Section.PREMIOS_CERTIFICADOS
                    Section.PUBLICACION -> MainActivityModel.Section.PUBLICACIONES
                    Section.PROYECTO -> MainActivityModel.Section.PROYECTOS
                    Section.HABILIDAD,
                    Section.IDIOMA,
                    Section.INTERES -> MainActivityModel.Section.MAS_SOBRE_MI
                }
            }.toSet()
            val currentState = MainActivityModel(sections)
            logger.trace("mainActivityState: {}", currentState)
            MainActivityState.Idle(currentState)
        }.catch {
            // todo emit
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = MainActivityState.Loading
        )
}