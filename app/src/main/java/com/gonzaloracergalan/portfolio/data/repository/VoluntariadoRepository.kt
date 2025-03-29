package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.VoluntariadoDAO
import com.gonzaloracergalan.portfolio.domain.model.Voluntariado
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory
import java.time.LocalDate

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
                    fechaFin = LocalDate.now(),
                    fechaInicio = LocalDate.parse(voluntariadoEntity.fechaInicio),
                    logros = voluntariadoEntity.logros,
                    organizacion = voluntariadoEntity.organizacion,
                    posicion = voluntariadoEntity.posicion,
                    resumen = voluntariadoEntity.resumen,
                    url = voluntariadoEntity.url
                )
            }
        }
}