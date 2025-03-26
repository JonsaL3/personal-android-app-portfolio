package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.BasicoDAO
import com.gonzaloracergalan.portfolio.domain.model.Direccion
import com.gonzaloracergalan.portfolio.domain.model.InformacionBasica
import com.gonzaloracergalan.portfolio.domain.model.RedSocial
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class BasicoRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("BasicoRepository")
    }

    private val basicoDAO: BasicoDAO by inject()

    val currentBasicoWithPerfilesFlow: Flow<InformacionBasica> = basicoDAO
        .getCurrentBasicoWithPerfilesFlow().map {
            logger.trace("currentBasicoWithPerfilesFlow")
            InformacionBasica(
                correo = it.basico.correo,
                cargoActual = it.basico.etiqueta,
                imagen = it.basico.imagen,
                resumen = it.basico.resumen,
                nombre = it.basico.nombre,
                telefono = it.basico.telefono,
                direccion = it.basico.ubicacion?.let { direccion ->
                    Direccion(
                        ciudad = direccion.ciudad,
                        codigoPais = direccion.codigoPais,
                        region = direccion.region,
                        codigoPostal = direccion.codigoPostal,
                        direccion = direccion.direccion
                    )
                },
                redesSociales = it.perfiles.map { perfil ->
                    RedSocial(
                        nombre = perfil.red,
                        url = perfil.url,
                        usuario = perfil.usuario
                    )
                }
            )
        }
}