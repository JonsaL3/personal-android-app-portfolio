package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.EducacionDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class EducacionRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("EducacionRepository")
    }

    private val dao: EducacionDAO by inject()
}