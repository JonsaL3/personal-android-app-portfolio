package com.gonzaloracergalan.portfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentExperienciaUseCase
import com.gonzaloracergalan.portfolio.ui.model.ExperienciaModel
import com.gonzaloracergalan.portfolio.ui.state.ExperienciaState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class ExperienciaViewModel : ViewModel(), KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger(ExperienciaViewModel::class.java)
    }

    private val getCurrentExperienciaUseCase: GetCurrentExperienciaUseCase by inject()

    val experienciaState: StateFlow<ExperienciaState> = getCurrentExperienciaUseCase().map {
        logger.trace("experienciaState: {}", it)
        val trabajos = it.trabajos.map { trabajo ->
            ExperienciaModel.Trabajo(
                fechaFin = trabajo.fechaFin,
                fechaInicio = trabajo.fechaInicio,
                logros = trabajo.logros,
                nombre = trabajo.nombre,
                posicion = trabajo.posicion,
                resumen = trabajo.resumen,
                url = trabajo.url
            )
        }
        val voluntariados = it.voluntariados.map { voluntariado ->
            ExperienciaModel.Voluntariado(
                fechaFin = voluntariado.fechaFin,
                fechaInicio = voluntariado.fechaInicio,
                logros = voluntariado.logros,
                organizacion = voluntariado.organizacion,
                posicion = voluntariado.posicion,
                resumen = voluntariado.resumen,
                url = voluntariado.url
            )
        }
        val model = ExperienciaModel(trabajos, voluntariados)
        logger.trace("experiencia: {}", model)
        ExperienciaState.Idle(model)
    }.catch {
        // todo handle error
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ExperienciaState.Loading
    )
}