package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.VoluntariadoDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class VoluntariadoRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("VoluntariadoRepository")
    }

    private val dao: VoluntariadoDAO by inject()
}