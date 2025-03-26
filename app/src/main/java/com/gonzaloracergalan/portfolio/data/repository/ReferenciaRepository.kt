package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.ReferenciaDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class ReferenciaRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("ReferenciaRepository")
    }

    private val dao: ReferenciaDAO by inject()
}