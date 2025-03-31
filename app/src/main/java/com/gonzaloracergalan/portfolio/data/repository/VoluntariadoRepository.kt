package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.VoluntariadoDAO
import com.gonzaloracergalan.portfolio.domain.model.Voluntariado
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory
import java.util.Date

class VoluntariadoRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("VoluntariadoRepository")
    }

    private val dao: VoluntariadoDAO by inject()

    val currentVoluntariadosFlow: Flow<List<Voluntariado>> =
        dao.getCurrentAllVoluntariadosFlow().map { list ->
            logger.trace("currentVoluntariadosFlow: {}", list)
            list.map { voluntariadoEntity ->
                Voluntariado(
                    fechaFin = voluntariadoEntity.fechaFinMillis?.let { Date(it) },
                    fechaInicio = voluntariadoEntity.fechaInicioMillis?.let { Date(it) },
                    logros = voluntariadoEntity.logros,
                    organizacion = voluntariadoEntity.organizacion,
                    posicion = voluntariadoEntity.posicion,
                    resumen = voluntariadoEntity.resumen,
                    url = voluntariadoEntity.url
                )
            }
        }
}