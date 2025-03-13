package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity
import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity
import com.gonzaloracergalan.portfolio.data.repository.BasicoRepository
import com.gonzaloracergalan.portfolio.data.repository.PerfilRepository
import com.gonzaloracergalan.portfolio.domain.util.PortfolioUseCase
import com.gonzaloracergalan.portfolio.domain.util.UseCaseResponse
import com.gonzaloracergalan.portfolio.ui.model.DireccionUI
import com.gonzaloracergalan.portfolio.ui.model.PerfilUI
import com.gonzaloracergalan.portfolio.ui.model.RedSocialUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentPerfilUiUseCase : PortfolioUseCase() {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentPerfilUiUseCase")
    }

    private val basicoRepository: BasicoRepository by inject()
    private val perfilRepository: PerfilRepository by inject()

    private val dataToUiMapper: (
        BasicoEntity,
        List<PerfilEntity>
    ) -> PerfilUI = { basico: BasicoEntity, perfiles: List<PerfilEntity> ->
        PerfilUI(
            correo = basico.correo ?: "ejemplo@ejemplo.es",
            cargoActual = basico.etiqueta ?: "Trabajador X",
            imagen = null, // todo
            nombre = basico.nombre ?: "John Doe",
            redesSociales = perfiles.map {
                RedSocialUI(
                    nombre = it.red ?: "Red Social",
                    url = it.url ?: "www.redsocial.ejemplo",
                    usuario = it.usuario ?: "@JohnDoe"
                )
            },
            resumen = basico.resumen ?: "Soy John Doe, un trabajador X",
            telefono = basico.telefono ?: "123 456 789",
            direccion = basico.ubicacion?.let {
                DireccionUI(
                    ciudad = it.ciudad ?: "Ciudad de ejemplo",
                    codigoPostal = it.codigoPostal,
                    codigoPais = it.codigoPais ?: "Pais de ejemplo",
                    region = it.region ?: "Region de ejemplo",
                    direccion = it.direccion
                )
            }
        )
    }

    operator fun invoke(): Flow<UseCaseResponse> {

        combine(
            basicoRepository.currentBasicoFlow.toUseCaseFlow(),
            perfilRepository.currentPerfilesOfCurrentBasicoFlow.toUseCaseFlow()
        ) { a, b ->

        }

    }
}