package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.TrabajoDAO
import com.gonzaloracergalan.portfolio.domain.model.Trabajo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.Date

class TrabajoRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("TrabajoRepository")
    }

    private val dao: TrabajoDAO by inject()

    val currentTrabajosFlow: Flow<List<Trabajo>> = dao.getCurrentAllTrabajosFlow().map { list ->
        logger.trace("currentTrabajosFlow: {}", list)
        list.map { trabajoEntity ->
            Trabajo(
                fechaFin = trabajoEntity.fechaFinMillis?.let { Date(it) },
                fechaInicio = trabajoEntity.fechaInicioMillis?.let { Date(it) },
                logros = trabajoEntity.logros ?: emptyList(),
                nombre = trabajoEntity.nombre,
                posicion = trabajoEntity.posicion,
                resumen = trabajoEntity.resumen,
                url = trabajoEntity.url,
            )
        }
    }
}