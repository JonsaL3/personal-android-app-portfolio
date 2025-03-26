package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.IdiomaDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class IdiomaRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("IdiomaRepository")
    }

    private val dao: IdiomaDAO by inject()
}