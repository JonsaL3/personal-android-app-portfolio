package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.data.repository.BasicoRepository
import com.gonzaloracergalan.portfolio.data.repository.PerfilRepository
import com.gonzaloracergalan.portfolio.domain.di.DomainResponse
import com.gonzaloracergalan.portfolio.ui.model.DireccionUI
import com.gonzaloracergalan.portfolio.ui.model.PerfilUI
import com.gonzaloracergalan.portfolio.ui.model.RedSocialUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class GetCurrentPerfilUiUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentPerfilUiUseCase")
    }

    private val basicoRepository: BasicoRepository by inject()
    private val perfilRepository: PerfilRepository by inject()

    operator fun invoke(): Flow<DomainResponse> = combine(
        basicoRepository.currentBasicoFlow,
        perfilRepository.currentPerfilesOfCurrentBasicoFlow
    ) { basicoEntity, perfilesEntities ->
        logger.info("invoke Se ha obtenido nueva información para el objeto PerfilUI, estableciendo...")
        return@combine basicoEntity?.let { mBasicoEntity ->
            val perfilUi = PerfilUI(
                correo = mBasicoEntity.correo ?: "ejemplo@ejemplo.es",
                cargoActual = mBasicoEntity.etiqueta ?: "Trabajador X",
                imagen = null, // todo
                nombre = mBasicoEntity.nombre ?: "John Doe",
                redesSociales = perfilesEntities.map {
                    RedSocialUI(
                        nombre = it.red ?: "Red Social",
                        url = it.url ?: "www.redsocial.ejemplo",
                        usuario = it.usuario ?: "@JohnDoe"
                    )
                },
                resumen = mBasicoEntity.resumen ?: "Soy John Doe, un trabajador X",
                telefono = mBasicoEntity.telefono ?: "123 456 789",
                direccion = mBasicoEntity.ubicacion?.let {
                    DireccionUI(
                        ciudad = it.ciudad ?: "Ciudad de ejemplo",
                        codigoPostal = it.codigoPostal,
                        codigoPais = it.codigoPais ?: "Pais de ejemplo",
                        region = it.region ?: "Region de ejemplo",
                        direccion = it.direccion
                    )
                }
            )
            logger.info("invoke Se ha establecido la información para el objeto PerfilUI")
            DomainResponse.Success(perfilUi)
        } ?: run {
            logger.warn("invoke No se ha encontrado información para el objeto PerfilUI")
            DomainResponse.Error(DomainResponse.DomainErrorType.NOT_FOUND_DATA)
        }
    }
}