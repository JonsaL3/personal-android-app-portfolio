package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.TrabajoDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class TrabajoRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("TrabajoRepository")
    }

    private val dao: TrabajoDAO by inject()
}