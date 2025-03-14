package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.db.relation.BasicoWithPerfilesRelation
import com.gonzaloracergalan.portfolio.data.repository.BasicoRepository
import com.gonzaloracergalan.portfolio.domain.util.PortfolioUseCase
import com.gonzaloracergalan.portfolio.domain.util.UseCaseResponse
import com.gonzaloracergalan.portfolio.ui.model.DireccionUI
import com.gonzaloracergalan.portfolio.ui.model.PerfilUI
import com.gonzaloracergalan.portfolio.ui.model.RedSocialUI
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentPerfilUiUseCase : PortfolioUseCase() {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentPerfilUiUseCase")
    }

    private val basicoRepository: BasicoRepository by inject()

    private val basicoWithPerfilesRelationToPerfilUi: (
        BasicoWithPerfilesRelation,
    ) -> PerfilUI = { basicoWithPerfiles: BasicoWithPerfilesRelation ->
        PerfilUI(
            correo = basicoWithPerfiles.basico.correo ?: "ejemplo@ejemplo.es",
            cargoActual = basicoWithPerfiles.basico.etiqueta ?: "Trabajador X",
            imagen = null, // todo
            nombre = basicoWithPerfiles.basico.nombre ?: "John Doe",
            redesSociales = basicoWithPerfiles.perfiles.map {
                RedSocialUI(
                    nombre = it.red ?: "Red Social",
                    url = it.url ?: "www.redsocial.ejemplo",
                    usuario = it.usuario ?: "@JohnDoe"
                )
            },
            resumen = basicoWithPerfiles.basico.resumen ?: "Soy John Doe, un trabajador X",
            telefono = basicoWithPerfiles.basico.telefono ?: "123 456 789",
            direccion = basicoWithPerfiles.basico.ubicacion?.let {
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
        logger.trace("invoke")
        return basicoRepository.currentBasicoWithPerfilesFlow.toUseCaseFlow(
            successMapper = basicoWithPerfilesRelationToPerfilUi
        )
    }
}