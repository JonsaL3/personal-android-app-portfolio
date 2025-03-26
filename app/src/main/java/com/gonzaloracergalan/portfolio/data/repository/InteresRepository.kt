package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.InteresDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class InteresRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("InteresRepository")
    }

    private val dao: InteresDAO by inject()
}