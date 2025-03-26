package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.PublicacionDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class PublicacionRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("PublicacionRepository")
    }

    private val dao: PublicacionDAO by inject()
}