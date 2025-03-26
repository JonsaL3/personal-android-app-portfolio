package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.PremioDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class PremioRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("PremioRepository")
    }

    private val dao: PremioDAO by inject()
}