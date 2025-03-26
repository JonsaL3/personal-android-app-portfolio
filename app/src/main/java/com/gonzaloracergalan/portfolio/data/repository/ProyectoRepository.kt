package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.ProyectoDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class ProyectoRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("ProyectoRepository")
    }

    private val dao: ProyectoDAO by inject()
}