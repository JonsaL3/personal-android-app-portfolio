package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.PerfilDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class PerfilRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("PerfilRepository")
    }

    private val dao: PerfilDAO by inject()
}