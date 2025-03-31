package com.gonzaloracergalan.portfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentInformacionBasicaUseCase
import com.gonzaloracergalan.portfolio.ui.model.InformacionGeneralModel
import com.gonzaloracergalan.portfolio.ui.state.InformacionGeneralState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class InformacionGeneralViewModel : ViewModel(), KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("InformacionGeneralViewModel")
    }

    private val getCurrentInformacionBasicaUseCase: GetCurrentInformacionBasicaUseCase by inject()

    val informacionGeneralState: StateFlow<InformacionGeneralState> = getCurrentInformacionBasicaUseCase()
        .map { useCaseFlow ->
            logger.trace("informacionGeneralState: {}", useCaseFlow)
            val informacionGeneral = InformacionGeneralModel(
                correo = useCaseFlow.correo,
                cargoActual = useCaseFlow.cargoActual,
                imagen = useCaseFlow.imagen,
                nombre = useCaseFlow.nombre,
                redesSociales = useCaseFlow.redesSociales.map {
                    InformacionGeneralModel.RedSocial(
                        nombre = it.nombre,
                        url = it.url,
                        usuario = it.usuario
                    )
                },
                resumen = useCaseFlow.resumen,
                telefono = useCaseFlow.telefono,
                ciudad = useCaseFlow.direccion?.ciudad,
                codigoPais = useCaseFlow.direccion?.codigoPais,
                region = useCaseFlow.direccion?.region,
                codigoPostal = useCaseFlow.direccion?.codigoPostal,
                direccion = useCaseFlow.direccion?.direccion
            )
            logger.trace("informacionGeneral: {}", informacionGeneral)
            InformacionGeneralState.Idle(informacionGeneral)
        }.catch {
            // todo handle error
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = InformacionGeneralState.Loading
        )
}