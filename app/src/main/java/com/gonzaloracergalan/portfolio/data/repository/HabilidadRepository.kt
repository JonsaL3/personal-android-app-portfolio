package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.HabilidadDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class HabilidadRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("HabilidadRepository")
    }

    private val dao: HabilidadDAO by inject()
}